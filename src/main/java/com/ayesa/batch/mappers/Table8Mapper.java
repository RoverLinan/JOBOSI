package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
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

        entity.put(COD_ATENCION.name(), "1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(COD_CENTRO_ATENCION.name(), "3");
        entity.put(COD_PRESUPUESTO.name(), "4");
        entity.put(TIP_CONEXION.name(), "5");
        entity.put(TIP_ACOMETIDA.name(), "6");
        entity.put(COD_TARIF_INI.name(), "7");
        entity.put(COD_TARIF_FIN.name(), "8");
        entity.put(POTENCIA.name(), "9");
    return entity;
    }
}
