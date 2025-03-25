package com.ayesa.batch.mappers.fields;

public enum Table2FieldEnum implements TableField {

    DESC_ACCION(TableFieldTypeEnum.STRING),
    COD_ESTADO_ATENCION(TableFieldTypeEnum.INTEGER),
    FEC_RESPUESTA(TableFieldTypeEnum.DATE),
    NUM_RESOLUCION(TableFieldTypeEnum.INTEGER),
    COMENTARIO_CLIENTE(TableFieldTypeEnum.STRING),
    COMENTARIO_USUARIO(TableFieldTypeEnum.STRING);

    private final TableFieldTypeEnum fieldType;

    Table2FieldEnum(TableFieldTypeEnum fieldType){
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
