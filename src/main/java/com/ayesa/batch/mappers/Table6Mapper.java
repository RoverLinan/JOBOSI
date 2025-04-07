package com.ayesa.batch.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
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

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, COD_SIST_ELECTRICO);
        getValueByType(entity, resultSet, COD_SED);
        getValueByType(entity, resultSet, COD_SECTOR_TIPICO);
        getValueByType(entity, resultSet, FEC_VER_CAMPO);
        getValueByType(entity, resultSet, COD_DEF_TIPICA);
        getValueByType(entity, resultSet, COD_UAP);
        getValueByType(entity, resultSet, FEC_SUB_DEFICIENCIA);
        getValueByType(entity, resultSet, NUM_ORD_TRABAJO);
        getValueByType(entity, resultSet, FEC_APLIACION);
        getValueByType(entity, resultSet, CANT_ATENCIONES);
        return entity;
    }

    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        throw new UnsupportedOperationException(" toListDTO - Operacion no soportada");
    }
}
