package com.ayesa.batch.enums;

public enum ParameterKitEnum {

    BATCH_OSI_PARAMETERS("OSINERGMIN","BATCH_OSI"),
    GENERIC_PARAMETERS("OSINERGMIN","PARAM_GEN");


    private final String parameterFamily;
    private final String parameterKit;

    ParameterKitEnum(String parameterFamily, String parameterKit){
        this.parameterFamily = parameterFamily;
        this.parameterKit = parameterKit;
    }

    public String getParameterFamily() {
        return parameterFamily;
    }

    public String getParameterKit() {
        return parameterKit;
    }
}
