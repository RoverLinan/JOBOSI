package com.ayesa.batch.util;

import com.ayesa.batch.BatchLauncher;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.ayesa.batch.enums.JobParameterEnum.HOLIDAYS;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void getCurrentDateTime() {
    }

    @Test
    public void getCurrentDateTimeSql() {
    }

    @Test
    public void formatDateTime() {
    }

    @Test
    public void adjustPeriodForBusinessDay_holidays_and_weekend() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATETIME_3);
        BatchLauncher.JOB_PARAMETERS.put(PERIODO_REMISION.name(), LocalDate.parse("30/07/2025", formatter ));
        BatchLauncher.JOB_PARAMETERS.put(HOLIDAYS.name(), "29/07/2025;28/07/2025");

        DateUtil.adjustPeriodForBusinessDay();

        Assert.assertEquals(0, LocalDate.parse("25/07/2025", formatter).compareTo((LocalDate)BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name())));

    }

    @Test
    public void adjustPeriodForBusinessDay_weekend() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATETIME_3);
        BatchLauncher.JOB_PARAMETERS.put(PERIODO_REMISION.name(), LocalDate.parse("04/08/2025", formatter ));
        BatchLauncher.JOB_PARAMETERS.put(HOLIDAYS.name(), "25/12/2025");

        DateUtil.adjustPeriodForBusinessDay();

        Assert.assertEquals(0, LocalDate.parse("01/08/2025", formatter).compareTo((LocalDate)BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name())));
    }

    @Test
    public void adjustPeriodForBusinessDay_holidays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATETIME_3);
        BatchLauncher.JOB_PARAMETERS.put(PERIODO_REMISION.name(), LocalDate.parse("24/07/2025", formatter ));
        BatchLauncher.JOB_PARAMETERS.put(HOLIDAYS.name(), "23/07/2025");

        DateUtil.adjustPeriodForBusinessDay();

        Assert.assertEquals(0, LocalDate.parse("22/07/2025", formatter).compareTo((LocalDate)BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name())));
    }

    @Test
    public void adjustPeriodForBusinessDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATETIME_3);
        BatchLauncher.JOB_PARAMETERS.put(PERIODO_REMISION.name(), LocalDate.parse("16/07/2025", formatter ));
        BatchLauncher.JOB_PARAMETERS.put(HOLIDAYS.name(), "23/07/2025");

        DateUtil.adjustPeriodForBusinessDay();

        Assert.assertEquals(0, LocalDate.parse("15/07/2025", formatter).compareTo((LocalDate)BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name())));
    }

    @Test
    public void parseFromString() {
    }

    @Test
    public void parseFromStringList() {
    }

    @Test
    public void parseDateFromPeriod() {
    }

    @Test
    public void parseDateToPeriod() {
    }

    @Test
    public void parseFromLocalDate() {
    }
}