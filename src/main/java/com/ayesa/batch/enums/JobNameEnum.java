package com.ayesa.batch.enums;

public enum JobNameEnum {
    JOB01("TABLE1"),
    JOB02("TABLE2"),
    JOB03("TABLE3"),
    JOB04("TABLE4"),
    JOB05("TABLE5"),
    JOB06("TABLE6"),
    JOB07("TABLE7"),
    JOB08("TABLE8");

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
