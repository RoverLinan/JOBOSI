package com.ayesa.batch.mappers.fields;

public enum MailTemplateFieldEnum {

    TABLE_NAME_FIELD("tableName"),
    PERIOD_FIELD("period"),
    COUNT_ERRORS_FIELD("countErrors"),
    COUNT_PROCESSED_FIELD("countProcessed"),
    DATE_FIELD("date"),
    USER_ID_FIELD("userId"),
    ERROR_TYPE_FIELD("errorType"),
    ERROR_CODE_FIELD("errorCode"),
    ERROR_DESCRIPTION_FIELD("errorDescription"),;


    private final String fieldName;
    MailTemplateFieldEnum(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }



}
