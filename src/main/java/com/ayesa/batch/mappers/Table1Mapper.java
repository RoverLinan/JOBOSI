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
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ESTADO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.FEC_CREACION;


public class Table1Mapper extends AbstractEntityMapper{


    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        entity.put(COD_EMPRESA.name(),"1");
        entity.put(COD_ATENCION.name(),"2");
        entity.put(FEC_CREACION.name(),"3");
        entity.put(COD_CANAL.name(),"4");
        entity.put(COD_TIP_DOCUMENTO.name(),"5");
        entity.put(NRO_DOCTO_IDENT.name(),"6");
        entity.put(NOMBRE_RAZON_SOCIAL.name(),"7");
        entity.put(APELLIDO_SOLICITANTE.name(),"8");
        entity.put(NUM_SUMINISTRO.name(),"9");
        entity.put(EMAIL_SOLICITANTE.name(),"10");
        entity.put(TELEF_SOLICITANTE.name(),"11");
        entity.put(DIRECCION.name(), "12");
        entity.put(UBIGEO.name(), "13");
        entity.put(COD_ASUNTO.name(), "14");
        entity.put(FEC_SOLUCION.name(), "15");
        entity.put(DESCRIPCION_RECLAMO.name(), "16");
        entity.put(COD_ESTADO.name(), "17");
        return entity;
    }

}
