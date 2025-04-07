package com.ayesa.batch.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static com.ayesa.batch.mappers.fields.Table8FieldEnum.COD_CENTRO_ATENCION;
import static com.ayesa.batch.mappers.fields.Table8FieldEnum.COD_PRESUPUESTO;
import static com.ayesa.batch.mappers.fields.Table8FieldEnum.COD_TARIF_FIN;
import static com.ayesa.batch.mappers.fields.Table8FieldEnum.COD_TARIF_INI;
import static com.ayesa.batch.mappers.fields.Table8FieldEnum.POTENCIA;
import static com.ayesa.batch.mappers.fields.Table8FieldEnum.TIP_ACOMETIDA;
import static com.ayesa.batch.mappers.fields.Table8FieldEnum.TIP_CONEXION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;


public class Table8Mapper extends AbstractEntityMapper {
    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, COD_CENTRO_ATENCION);
        getValueByType(entity, resultSet, COD_PRESUPUESTO);
        getValueByType(entity, resultSet, TIP_CONEXION);
        getValueByType(entity, resultSet, TIP_ACOMETIDA);
        getValueByType(entity, resultSet, COD_TARIF_INI);
        getValueByType(entity, resultSet, COD_TARIF_FIN);
        getValueByType(entity, resultSet, POTENCIA);
    return entity;
    }

    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        throw new UnsupportedOperationException(" toListDTO - Operacion no soportada");
    }
}
