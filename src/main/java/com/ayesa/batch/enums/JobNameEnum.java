package com.ayesa.batch.enums;

public enum JobNameEnum {
    JOB01("TISEC_TT_1"),
    JOB02("TISEC_TT_2"),
    JOB03("TISEC_TT_3"),
    JOB04("TISEC_TT_4"),
    JOB05("TISEC_TT_5"),
    JOB06("TISEC_TT_6"),
    JOB07("TISEC_TT_7"),
    JOB08("TISEC_TT_8"),
    ERROR_OSI("OSI_ERRORES_REMISION"),
    COM_PARAMETERS("OSI_COM_PARAMETROS");

    private final String tableName;
    JobNameEnum(String tableName){
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        return "JobNameEnum{" +
                "tableName='" + tableName + '\'' +
                "} " + super.toString();
    }
}
