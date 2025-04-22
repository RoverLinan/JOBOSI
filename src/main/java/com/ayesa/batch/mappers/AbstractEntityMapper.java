package com.ayesa.batch.mappers;

import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.mappers.fields.TableField;
import com.ayesa.batch.mappers.fields.TableFieldTypeEnum;
import com.ayesa.batch.util.DateUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractEntityMapper {

    public abstract Map<String, Object> toEntity(ResultSet resultSet);


    public abstract List<Serializable> toListDTO(List<Map<String, Object>> data);
    public void getValueByType(Map<String, Object> map, ResultSet resultSet, TableField field){
        if(Objects.isNull(resultSet)){
            map.put(field.getFieldName(), null);
        }

        try {
            TableFieldTypeEnum fieldType = field.getFieldType();
            switch (fieldType) {
                case STRING:
                    map.put( field.getFieldName(), trim( resultSet.getString(field.getFieldName())) );
                    break;
                case INTEGER:
                    map.put( field.getFieldName(), resultSet.getInt(field.getFieldName()) );
                    break;
                case DATE:
                    map.put( field.getFieldName(), resultSet.getTimestamp(field.getFieldName()) );
                    break;
                case DECIMAL:
                    map.put( field.getFieldName(), resultSet.getDouble(field.getFieldName()) );
                    break;
            }
        } catch (Exception e) {
            map.put(field.getFieldName(), null);
            System.out.println("Error al obtener el valor del campo: " + field.getFieldName());
        }
    }

    private String trim(String value){
        if(Objects.isNull(value)){
            return null;
        }
        return value.trim();
    }

    protected void changeFormatDate(Map<String, Object> entity, TableField field, String formatSource, String formatTarget) {

        if (Objects.nonNull(entity.get(field.getFieldName()))) {
            entity.replace(field.getFieldName(), DateUtil.formatDateTime( entity.get(field.getFieldName()).toString(), formatSource, formatTarget));
        }
    }
}
