package com.ayesa.batch.enums;

public enum HttpMethodEnum {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH;

    @Override
    public String toString() {
        return name();
    }
}
