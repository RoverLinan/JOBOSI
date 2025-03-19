package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ayesa.batch.mappers.fields.Table6FieldEnum.COD_DEF_TIPICA;
import static com.ayesa.batch.mappers.fields.Table6FieldEnum.COD_SECTOR_TIPICO;
import static com.ayesa.batch.mappers.fields.Table6FieldEnum.COD_UAP;
import static com.ayesa.batch.mappers.fields.Table6FieldEnum.FEC_APLIACION;
import static com.ayesa.batch.mappers.fields.Table6FieldEnum.FEC_SUB_DEFICIENCIA;
import static com.ayesa.batch.mappers.fields.Table6FieldEnum.FEC_VER_CAMPO;
import static com.ayesa.batch.mappers.fields.Table6FieldEnum.NUM_ORD_TRABAJO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.CANT_ATENCIONES;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_SED;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_SIST_ELECTRICO;

public class Table6Mapper extends AbstractEntityMapper{
    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        entity.put(COD_ATENCION.name(), "1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(COD_SIST_ELECTRICO.name(), "3");
        entity.put(COD_SED.name(), "4");
        entity.put(COD_SECTOR_TIPICO.name(), "5");
        entity.put(FEC_VER_CAMPO.name(), "6");
        entity.put(COD_DEF_TIPICA.name(), "7");
        entity.put(COD_UAP.name(), "8");
        entity.put(FEC_SUB_DEFICIENCIA.name(), "9");
        entity.put(NUM_ORD_TRABAJO.name(), "10");
        entity.put(FEC_APLIACION.name(), "11");
        entity.put(CANT_ATENCIONES.name(), "12");

        return entity;
    }
}
