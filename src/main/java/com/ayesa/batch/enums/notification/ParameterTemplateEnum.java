package com.ayesa.batch.enums.notification;

public enum ParameterTemplateEnum {

    TABLE_NAME("{{tableName}}"),
    COUNT_ERRORS("{{countErrors}}"),
    COUNT_PROCESSED("{{countProcessed}}"),
    EXECUTION_DATE("{{executionDate}}"),
    USER_ID("{{userId}}"),
    EXECUTION_PERIOD("{{executionPeriod}}");


    private final String parameterName;
    ParameterTemplateEnum(String parameterName) {
        this.parameterName = parameterName;
    }
    public String getParameterName() {
        return parameterName;
    }





}
