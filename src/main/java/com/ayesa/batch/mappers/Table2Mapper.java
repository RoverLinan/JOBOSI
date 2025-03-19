package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

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

        entity.put(COD_ATENCION.name(), "1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(FEC_CREACION.name(), "3");
        entity.put(DESC_ACCION.name(), "4");
        entity.put(COD_ESTADO_ATENCION.name(), "5");
        entity.put(FEC_RESPUESTA.name(), "6");
        entity.put(NUM_RESOLUCION.name(), "7");
        entity.put(COMENTARIO_CLIENTE.name(), "8");
        entity.put(COMENTARIO_USUARIO.name(), "9");
        entity.put(COD_ESTADO.name(), "10");


        return entity;
    }
}
