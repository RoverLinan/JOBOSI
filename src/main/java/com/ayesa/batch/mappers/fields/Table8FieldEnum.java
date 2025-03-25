package com.ayesa.batch.mappers.fields;

public enum Table8FieldEnum implements TableField {

    COD_CENTRO_ATENCION(TableFieldTypeEnum.STRING),
    COD_PRESUPUESTO(TableFieldTypeEnum.STRING),
    TIP_CONEXION(TableFieldTypeEnum.STRING),
    TIP_ACOMETIDA(TableFieldTypeEnum.STRING),
    COD_TARIF_INI(TableFieldTypeEnum.INTEGER),
    COD_TARIF_FIN(TableFieldTypeEnum.INTEGER),
    POTENCIA(TableFieldTypeEnum.DECIMAL);

    private final TableFieldTypeEnum fieldType;

    Table8FieldEnum(TableFieldTypeEnum fieldType){
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
