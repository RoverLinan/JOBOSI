package com.ayesa.batch.mappers.fields;

public enum Table4FieldEnum implements TableField {



    COD_TIP_CONEX(TableFieldTypeEnum.INTEGER),
    CANT_USU_AFECTADOS(TableFieldTypeEnum.INTEGER),
    SUM_AFECTADOS(TableFieldTypeEnum.STRING),
    COD_CAUSA_VAR_TENSION(TableFieldTypeEnum.INTEGER),
    SUST_TEC_ORIGEN(TableFieldTypeEnum.STRING),
    COD_MED_ATENCION(TableFieldTypeEnum.INTEGER),
    FEC_ULT_APLICACION(TableFieldTypeEnum.DATE),
    SUST_TEC_CONCLUYE(TableFieldTypeEnum.STRING);

    private final TableFieldTypeEnum fieldType;

    Table4FieldEnum(TableFieldTypeEnum fieldType){
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
