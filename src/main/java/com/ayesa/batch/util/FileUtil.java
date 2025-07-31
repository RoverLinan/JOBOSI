package com.ayesa.batch.util;

import com.ayesa.batch.business.exception.LogicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    public static final String PATH_RESOURCES_SQL_QUERIES = "sql/sql-TABLES.properties";
    public static final String PATH_FILES_UPLOAD = "files/upload";

    public static final String PATH_RESOURCE_TEMPLATE_MAIL = "templates/mail-error.properties";

    private static final String SEPARATOR_FILE_NAME = "_";


    public static Properties getPropertiesFromResources(String path) {

        Properties properties = new Properties();
        try (InputStream input = FileUtil.class.getClassLoader().getResourceAsStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static void createFolder(){
        File folder = new File(PATH_FILES_UPLOAD);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void writeMapToFile(List<Map<String, Object>> rows, String fileName, String delimiter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, Boolean.TRUE))) {
            for (Map<String, Object> row : rows) {

                StringBuilder line = new StringBuilder();
                for (String key : row.keySet()) {
                    if (line.length() > 0) {
                        line.append(delimiter);
                    }

                    if(Objects.nonNull(row.get(key))) {
                        line.append( row.get(key));
                    }

                }
                writer.write(line.append(delimiter).toString());
                writer.newLine();
            }
            LOGGER.info("Archivo {} creado exitosamente.", fileName);
        } catch (IOException e) {
            LOGGER.error("Error al escribir en el archivo {}: {}", fileName, e.getMessage());
        }
    }


    public static String createFileName(String name, FileUtil.FileTypeEnum fileType){
        if(Objects.nonNull(name)){
            long timestamp = Instant.now().getLong(ChronoField.INSTANT_SECONDS);
            StringBuilder sb = new StringBuilder(PATH_FILES_UPLOAD);
            sb.append(System.getProperty("file.separator")).append(name.toUpperCase()).append(SEPARATOR_FILE_NAME).append(timestamp).append(".").append(fileType.name().toLowerCase());
            return sb.toString();
        }
        throw new LogicalException("004",
                Boolean.FALSE,
                "Error al crear el nombre de archivo",
                new IllegalArgumentException("if(Objects.nonNull(name)){"));
    }

    public static Properties loadProperties(String path){
        Properties properties = new Properties();
        File file = new File(path);
        try (FileInputStream input = new FileInputStream(file)) {
            properties.load(input);
        }catch (IOException io){
            throw new LogicalException("OO1",Boolean.FALSE, io.getLocalizedMessage(), io );
        }
        return properties;
    }


    public enum FileTypeEnum{

        TXT,
        PDF,
        ZIP
    }
}
