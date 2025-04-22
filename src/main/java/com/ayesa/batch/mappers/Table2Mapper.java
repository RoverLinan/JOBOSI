package com.ayesa.batch.mappers;

import com.ayesa.batch.util.DateUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ayesa.batch.mappers.fields.Table2FieldEnum.COD_ESTADO_ATENCION;
import static com.ayesa.batch.mappers.fields.Table2FieldEnum.COMENTARIO_CLIENTE;
import static com.ayesa.batch.mappers.fields.Table2FieldEnum.COMENTARIO_USUARIO;
import static com.ayesa.batch.mappers.fields.Table2FieldEnum.DESC_ACCION;
import static com.ayesa.batch.mappers.fields.Table2FieldEnum.FEC_RESPUESTA;
import static com.ayesa.batch.mappers.fields.Table2FieldEnum.NUM_RESOLUCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ESTADO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.FEC_CREACION;

public class Table2Mapper extends AbstractEntityMapper {
    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, FEC_CREACION);
        getValueByType(entity, resultSet, DESC_ACCION);
        getValueByType(entity, resultSet, COD_ESTADO_ATENCION);
        getValueByType(entity, resultSet, FEC_RESPUESTA);
        getValueByType(entity, resultSet, NUM_RESOLUCION);
        getValueByType(entity, resultSet, COMENTARIO_CLIENTE);
        getValueByType(entity, resultSet, COMENTARIO_USUARIO);
        getValueByType(entity, resultSet, COD_ESTADO);

        changeFormatDate(entity, FEC_CREACION, DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2);
        changeFormatDate(entity, FEC_RESPUESTA, DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_3);

        return entity;
    }

    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        throw new UnsupportedOperationException(" toListDTO - Operacion no soportada");
    }
}
