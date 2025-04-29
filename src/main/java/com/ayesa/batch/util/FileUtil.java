package com.ayesa.batch.util;

import com.ayesa.batch.business.exception.LogicalException;

import java.io.*;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class FileUtil {
    private static final String PATH_RESOURCES = "sql/sql-TABLES.properties";
    public static final String PATH_FILES_UPLOAD = "files/upload";

    private static final String SEPARATOR_FILE_NAME = "_";

    private static Properties propertiesResources;
    public static Properties getPropertiesFromResources(){

        if(Objects.isNull(propertiesResources)) {
            Properties properties = new Properties();
            try (InputStream input = FileUtil.class.getClassLoader().getResourceAsStream(PATH_RESOURCES)) {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            propertiesResources = properties;
        }
        return propertiesResources;
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
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Datos escritos correctamente en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
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
