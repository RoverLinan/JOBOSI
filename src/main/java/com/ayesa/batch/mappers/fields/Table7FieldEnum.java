package com.ayesa.batch.mappers.fields;

public enum Table7FieldEnum implements TableField {


    TIP_ELE_RIESGOELEC(TableFieldTypeEnum.INTEGER),
    COD_ELE_RIESGOELEC(TableFieldTypeEnum.STRING),
    EVA_CAMPO(TableFieldTypeEnum.STRING),
    CAUSA_RIESGO(TableFieldTypeEnum.STRING),
    DESC_MED_PREVENTIVAS(TableFieldTypeEnum.STRING),
    SUBS_RIESGO_ELECTRICO(TableFieldTypeEnum.INTEGER),
    CAUSA_NO_CUMPLIMIENTO(TableFieldTypeEnum.STRING);


    private final TableFieldTypeEnum fieldType;

    Table7FieldEnum(TableFieldTypeEnum fieldType){
        this.fieldType = fieldType;
    }

    @Override
    public TableFieldTypeEnum getFieldType() {
        return fieldType;
    }

    @Override
    public String getFieldName() {
        return name();
    }

}
