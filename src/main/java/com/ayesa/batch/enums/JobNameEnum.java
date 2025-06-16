package com.ayesa.batch.enums;

public enum JobNameEnum {
    JOB01("TISEC_TT_1", "TABLA1"),
    JOB02("TISEC_TT_2","TABLA2"),
    JOB03("TISEC_TT_3","TABLA3"),
    JOB04("TISEC_TT_4","TABLA4"),
    JOB05("TISEC_TT_5","TABLA5"),
    JOB06("TISEC_TT_6","TABLA6"),
    JOB07("TISEC_TT_7","TABLA7"),
    JOB08("TISEC_TT_8","TABLA8"),
    ERROR_OSI("OSI_ERRORES_REMISION", ""),
    COM_PARAMETERS("OSI_COM_PARAMETROS", ""),

    PACKAGE_CORREO_NOTIFICATION("OSI_PQ_CORREO_NOTIFICATION", "");

    private final String tableName;
    private final String tableNameBD;
    JobNameEnum(String tableName, String tableNameBD){
        this.tableName = tableName;
        this.tableNameBD = tableNameBD;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableNameBD() {
        return tableNameBD;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JobNameEnum{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", tableNameBD='").append(tableNameBD).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
