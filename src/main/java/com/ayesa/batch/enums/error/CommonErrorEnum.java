package com.ayesa.batch.enums.error;

public enum CommonErrorEnum {
    STATUS_PROCESSING( "El registro se encuentra en estado de procesamiento");
    private final String message;
    CommonErrorEnum(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
