package com.ayesa.batch.mappers.fields;

public enum Table3FieldEnum  implements TableField {

    COD_INTERRUPCION(TableFieldTypeEnum.STRING),
    FEC_INI_INTERRUPCION(TableFieldTypeEnum.DATE),
    FEC_FIN_INTERRUPCION(TableFieldTypeEnum.DATE),
    CANT_SUM_AFECTADOS(TableFieldTypeEnum.INTEGER),
    FASE_INTERRUMPIDA(TableFieldTypeEnum.STRING),
    POTENCIA_INTERRUMPIDA(TableFieldTypeEnum.DECIMAL),
    ENERGIA_NO_SUMINISTRADA(TableFieldTypeEnum.DECIMAL),
    COD_MOTIVO_FALLA(TableFieldTypeEnum.INTEGER),
    DESCRIPCION_MOTIVO(TableFieldTypeEnum.STRING),
    LOCALIZACION_FALLA(TableFieldTypeEnum.STRING),
    SUST_TEC_A_INTERR_4_12(TableFieldTypeEnum.STRING),
    SUST_TEC_A_INTERR_MAS_12(TableFieldTypeEnum.STRING),
    SUST_TEC_C(TableFieldTypeEnum.STRING),
    DESCR_ACC_REALIZADAS(TableFieldTypeEnum.STRING),
    CARACT_TEC_PUESTA_OPER(TableFieldTypeEnum.STRING),
    ACTA_INSPECCION(TableFieldTypeEnum.STRING);

    private final TableFieldTypeEnum fieldType;

    Table3FieldEnum(TableFieldTypeEnum fieldType){
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
