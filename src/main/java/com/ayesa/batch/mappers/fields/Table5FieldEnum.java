package com.ayesa.batch.mappers.fields;

public enum Table5FieldEnum implements TableField {


    COD_IRREGULARIDAD(TableFieldTypeEnum.INTEGER),
    ZON_GEOGRAFICA(TableFieldTypeEnum.INTEGER),
    CANT_SUM_AFECTADOS(TableFieldTypeEnum.INTEGER),
    MES_FAC_IRREGULARIDAD(TableFieldTypeEnum.STRING),
    MED_CORRECTIVA(TableFieldTypeEnum.STRING),
    SUST_TECNICO(TableFieldTypeEnum.STRING);

    private final TableFieldTypeEnum fieldType;

    Table5FieldEnum(TableFieldTypeEnum fieldType){
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
