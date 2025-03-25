package com.ayesa.batch.mappers.fields;

public enum Table6FieldEnum implements TableField {


    COD_SECTOR_TIPICO(TableFieldTypeEnum.INTEGER),
    FEC_VER_CAMPO(TableFieldTypeEnum.DATE),
    COD_DEF_TIPICA(TableFieldTypeEnum.INTEGER),
    COD_UAP(TableFieldTypeEnum.STRING),
    FEC_SUB_DEFICIENCIA(TableFieldTypeEnum.DATE),
    NUM_ORD_TRABAJO(TableFieldTypeEnum.STRING),
    FEC_APLIACION(TableFieldTypeEnum.DATE);

    private final TableFieldTypeEnum fieldType;


    Table6FieldEnum(TableFieldTypeEnum fieldType){
        this.fieldType = fieldType;
    }

    @Override
    public TableFieldTypeEnum getFieldType() {
        return TableFieldTypeEnum.STRING;
    }

    @Override
    public String getFieldName() {
        return name();
    }


}
