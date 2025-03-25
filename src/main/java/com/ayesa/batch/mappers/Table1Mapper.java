package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ayesa.batch.mappers.fields.Table1FieldEnum.APELLIDO_SOLICITANTE;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_ASUNTO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_CANAL;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_EMPRESA;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_TIP_DOCUMENTO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.DESCRIPCION_RECLAMO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.DIRECCION;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.EMAIL_SOLICITANTE;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.FEC_SOLUCION;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.NOMBRE_RAZON_SOCIAL;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.NRO_DOCTO_IDENT;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.NUM_SUMINISTRO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.TELEF_SOLICITANTE;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.UBIGEO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_DISTRITO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ESTADO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.FEC_CREACION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.TIP_ATENCION;


public class Table1Mapper extends AbstractEntityMapper{


    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_EMPRESA);
        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, FEC_CREACION);
        getValueByType(entity, resultSet, COD_CANAL);
        getValueByType(entity, resultSet, COD_TIP_DOCUMENTO);
        getValueByType(entity, resultSet, NRO_DOCTO_IDENT);
        getValueByType(entity, resultSet, NOMBRE_RAZON_SOCIAL);
        getValueByType(entity, resultSet, APELLIDO_SOLICITANTE);
        getValueByType(entity, resultSet, NUM_SUMINISTRO);
        getValueByType(entity, resultSet, EMAIL_SOLICITANTE);
        getValueByType(entity, resultSet, TELEF_SOLICITANTE);
        getValueByType(entity, resultSet, DIRECCION);
        getValueByType(entity, resultSet, UBIGEO);
        getValueByType(entity, resultSet, COD_ASUNTO);
        getValueByType(entity, resultSet, FEC_SOLUCION);
        getValueByType(entity, resultSet, DESCRIPCION_RECLAMO);
        getValueByType(entity, resultSet, COD_ESTADO);
        getValueByType(entity, resultSet, TIP_ATENCION);
        getValueByType(entity, resultSet, COD_DISTRITO);

        return entity;
    }

}
