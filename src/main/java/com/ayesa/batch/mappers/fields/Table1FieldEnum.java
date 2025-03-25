package com.ayesa.batch.mappers.fields;

public enum Table1FieldEnum implements TableField {
    COD_EMPRESA(TableFieldTypeEnum.STRING),
    COD_CANAL(TableFieldTypeEnum.STRING),
    COD_TIP_DOCUMENTO(TableFieldTypeEnum.INTEGER),
    NRO_DOCTO_IDENT(TableFieldTypeEnum.STRING),
    NOMBRE_RAZON_SOCIAL(TableFieldTypeEnum.STRING),
    APELLIDO_SOLICITANTE(TableFieldTypeEnum.STRING),
    NUM_SUMINISTRO(TableFieldTypeEnum.STRING),
    EMAIL_SOLICITANTE(TableFieldTypeEnum.STRING),
    TELEF_SOLICITANTE(TableFieldTypeEnum.STRING),
    DIRECCION(TableFieldTypeEnum.STRING),
    UBIGEO(TableFieldTypeEnum.STRING),
    COD_ASUNTO(TableFieldTypeEnum.INTEGER),
    FEC_SOLUCION(TableFieldTypeEnum.DATE),
    DESCRIPCION_RECLAMO(TableFieldTypeEnum.STRING);

    private final TableFieldTypeEnum fieldType;

    Table1FieldEnum(TableFieldTypeEnum fieldType){
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
