package com.ayesa.batch.enums;

import java.util.Arrays;

public enum QueryNameEnum {

    SQL_TABLE1_SELECT_ALL(              JobNameEnum.JOB01,QueryFunctionEnum.SELECT, "sql.table1.select.all"),
    SQL_TABLE1_SELECT_COUNT_ALL(        JobNameEnum.JOB01,QueryFunctionEnum.COUNT, "sql.table1.select.count.all"),

    SQL_TABLE2_SELECT_ALL(              JobNameEnum.JOB02,QueryFunctionEnum.SELECT, "sql.table2.select.all"),
    SQL_TABLE2_SELECT_COUNT_ALL(        JobNameEnum.JOB02,QueryFunctionEnum.COUNT, "sql.table2.select.count.all"),

    SQL_TABLE3_SELECT_ALL(              JobNameEnum.JOB03,QueryFunctionEnum.SELECT, "sql.table3.select.all"),
    SQL_TABLE3_SELECT_COUNT_ALL(        JobNameEnum.JOB03,QueryFunctionEnum.COUNT, "sql.table3.select.count.all"),

    SQL_TABLE4_SELECT_ALL(              JobNameEnum.JOB04,QueryFunctionEnum.SELECT, "sql.table4.select.all"),
    SQL_TABLE4_SELECT_COUNT_ALL(        JobNameEnum.JOB04,QueryFunctionEnum.COUNT, "sql.table4.select.count.all"),

    SQL_TABLE5_SELECT_ALL(              JobNameEnum.JOB05,QueryFunctionEnum.SELECT, "sql.table5.select.all"),
    SQL_TABLE5_SELECT_COUNT_ALL(        JobNameEnum.JOB05,QueryFunctionEnum.COUNT, "sql.table5.select.count.all"),

    SQL_TABLE6_SELECT_ALL(              JobNameEnum.JOB06,QueryFunctionEnum.SELECT, "sql.table6.select.all"),
    SQL_TABLE6_SELECT_COUNT_ALL(        JobNameEnum.JOB06,QueryFunctionEnum.COUNT, "sql.table6.select.count.all"),

    SQL_TABLE7_SELECT_ALL(              JobNameEnum.JOB07,QueryFunctionEnum.SELECT, "sql.table7.select.all"),
    SQL_TABLE7_SELECT_COUNT_ALL(        JobNameEnum.JOB07,QueryFunctionEnum.COUNT, "sql.table7.select.count.all"),

    SQL_TABLE8_SELECT_ALL(              JobNameEnum.JOB08,QueryFunctionEnum.SELECT, "sql.table8.select.all"),
    SQL_TABLE8_SELECT_COUNT_ALL(        JobNameEnum.JOB08,QueryFunctionEnum.COUNT, "sql.table8.select.count.all");





    private final JobNameEnum jobName;
    private final QueryFunctionEnum queryFunction;
    private final String propertyName;
    QueryNameEnum(JobNameEnum jobName, QueryFunctionEnum queryFunction, String propertyName){
        this.jobName = jobName;
        this.queryFunction = queryFunction;
        this.propertyName = propertyName;
    }

    public JobNameEnum getJobName() {
        return jobName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public QueryFunctionEnum getQueryFunction() {
        return queryFunction;
    }

    public static QueryNameEnum inverse(JobNameEnum jobName, QueryFunctionEnum queryFunction){

        return Arrays.stream(values())
                .filter( x ->
                        x.jobName.equals(jobName) && x.queryFunction.equals(queryFunction)
                )
                .findFirst().orElseThrow(
            () ->     new IllegalArgumentException("inverseByJobName: JobName no encontrado")
        );
    }

    public enum QueryFunctionEnum{
        SELECT,
        INSERT,
        DELETE,
        UPDATE,
        COUNT,
        GROUP,
        ORDER_ASC,
        ORDER_DESC



    }

}
