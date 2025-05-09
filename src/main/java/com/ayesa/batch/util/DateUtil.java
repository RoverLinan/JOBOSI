package com.ayesa.batch.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

    public static String FORMAT_DATETIME_1 = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT_DATETIME_2 = "dd/MM/yyyy HH:mm";
    public static String FORMAT_DATETIME_3 = "dd/MM/yyyy";

    public static String FORMAT_DATETIME_4 = "d 'de' MMMM 'del' yyyy hh:mm:ss a";


    public static String getCurrentDateTime(String format) {
        // Zona horaria de Perú
        ZoneId zonaPeru = ZoneId.of("America/Lima");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zonaPeru);

        LocalDateTime currentDate = zonedDateTime.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, new Locale("es-PE"));
        return currentDate.format(formatter);
    }

    public static Timestamp getCurrentDateTimeSql(String format) {
        // Zona horaria de Perú
        ZoneId zonaPeru = ZoneId.of("America/Lima");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zonaPeru);
        LocalDateTime currentDate = zonedDateTime.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedDate = currentDate.format(formatter);
        return Timestamp.valueOf(formattedDate);
    }

    public static String formatDateTime(String dateTime, String inputFormat, String outputFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);

        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, inputFormatter);
        return parsedDateTime.format(outputFormatter);
    }

}
