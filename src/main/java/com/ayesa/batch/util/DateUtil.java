package com.ayesa.batch.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {


    public static String getCurrentDateTime(String format) {
        // Zona horaria de Perú
        ZoneId zonaPeru = ZoneId.of("America/Lima");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zonaPeru);

        LocalDateTime currentDate = zonedDateTime.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return currentDate.format(formatter);
    }
}
