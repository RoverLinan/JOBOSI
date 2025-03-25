package com.ayesa.batch.mappers.fields;

public enum TableCommonFieldEnum implements TableField {
    COD_ATENCION(TableFieldTypeEnum.STRING),
    COD_ACCION(TableFieldTypeEnum.STRING),
    FEC_CREACION(TableFieldTypeEnum.DATE),
    COD_ALIMENTADOR(TableFieldTypeEnum.STRING),
    COD_SED(TableFieldTypeEnum.STRING),
    COORD_UTM_X(TableFieldTypeEnum.DECIMAL),
    COORD_UTM_Y(TableFieldTypeEnum.DECIMAL),
    CANT_ATENCIONES(TableFieldTypeEnum.INTEGER),
    COD_SIST_ELECTRICO(TableFieldTypeEnum.STRING),
    TIP_ATENCION(TableFieldTypeEnum.STRING),

    COD_DISTRITO(TableFieldTypeEnum.INTEGER),
    COD_ESTADO(TableFieldTypeEnum.STRING);

    private final TableFieldTypeEnum fieldType;

    TableCommonFieldEnum(TableFieldTypeEnum fieldType){
        this.fieldType = fieldType;
    }

    @Override
    public String getFieldName() {
        return name();
    }


    @Override
    public TableFieldTypeEnum getFieldType() {
        return fieldType;
    }
}
