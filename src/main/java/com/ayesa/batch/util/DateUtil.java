package com.ayesa.batch.util;

import com.ayesa.batch.BatchLauncher;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ayesa.batch.enums.JobParameterEnum.HOLIDAYS;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;

public class DateUtil {

    public static String FORMAT_DATETIME_1 = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT_DATETIME_2 = "dd/MM/yyyy HH:mm";
    public static String FORMAT_DATETIME_3 = "dd/MM/yyyy";

    public static String FORMAT_DATE_4 = "YYYY-MM-DD";

    public static String FORMAT_DATETIME_4 = "d 'de' MMMM 'del' yyyy hh:mm:ss a";


    public static String getCurrentDateTime(String format) {
        // Zona horaria de Perú
        ZoneId zonaPeru = ZoneId.of("America/Lima");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zonaPeru);

        LocalDateTime currentDate = zonedDateTime.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, new Locale("es-PE"));
        return currentDate.format(formatter);
    }

    public static String getCurrentDate(String format) {
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



    public static void adjustPeriodForBusinessDay() {
        LocalDate periodDate = (LocalDate) BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name());
        String holidayDatesInYear = (String) BatchLauncher.JOB_PARAMETERS.get(HOLIDAYS.name());

        // This method should return the next business day after a given holiday date in the year.
        // For simplicity, let's assume the holidayDateInYear is a string in the format "dd/MM/yyyy;dd/MM/yyyy;..."

        periodDate = periodDate.minusDays(1);

        periodDate = adjustForWeekend(periodDate);

        List<LocalDate> holidays = parseFromStringList(holidayDatesInYear, FORMAT_DATETIME_3);
        while (isHoliday(periodDate, holidays)) {
            // If it's a holiday, move to the before day
            periodDate = periodDate.minusDays(1);
            periodDate = adjustForWeekend(periodDate);
        }

        BatchLauncher.JOB_PARAMETERS.put(PERIODO_REMISION.name(), periodDate);
    }



    public static LocalDate parseFromString( String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static List<LocalDate> parseFromStringList(String dates, String format) {
        if(dates == null || dates.isEmpty()){
            throw new IllegalArgumentException("La cadena de fechas  de feriados no puede ser nula o vacía.");
        }
        return Stream.of(dates.split(";"))
                .map(date -> parseFromString(date, format))
                .collect(Collectors.toList());
    }
    public static LocalDate parseDateFromPeriod(String input) {
        if (input.length() != 6) {
            throw new IllegalArgumentException("Formato incorrecto. Debe tener 6 dígitos: yymmdd.");
        }

        int year = 2000 + Integer.parseInt(input.substring(0, 2));
        int month = Integer.parseInt(input.substring(2, 4));
        int day = Integer.parseInt(input.substring(4, 6));

        return LocalDate.of(year, month, day);
    }

    public static String parseDateToPeriod(LocalDate input) {
        if (input == null) {
            throw new IllegalArgumentException("La fecha de entrada no puede ser nula.");
        }

        input = input.minusDays(1);

        String year = String.format("%02d", input.getYear() % 100);
        String month = String.format("%02d", input.getMonthValue());
        String day = String.format("%02d", input.getDayOfMonth());

        return year + month + day;
    }

    public static String parseFromLocalDate(Object input){
        return ((LocalDate)input).toString();
    }

    @NotNull
    private static LocalDate adjustForWeekend(LocalDate periodDate) {
        while (periodDate.getDayOfWeek().getValue() >= 6) {
            // If it's a weekend, move to the before day
            periodDate = periodDate.minusDays(1);
        }
        return periodDate;
    }

    private static boolean isHoliday(LocalDate date, List<LocalDate> holidays) {
        return holidays.contains(date);
    }




}
