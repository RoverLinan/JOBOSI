package com.ayesa.batch.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.ayesa.batch.mappers.fields.Table7FieldEnum.CAUSA_NO_CUMPLIMIENTO;
import static com.ayesa.batch.mappers.fields.Table7FieldEnum.CAUSA_RIESGO;
import static com.ayesa.batch.mappers.fields.Table7FieldEnum.COD_ELE_RIESGOELEC;
import static com.ayesa.batch.mappers.fields.Table7FieldEnum.DESC_MED_PREVENTIVAS;
import static com.ayesa.batch.mappers.fields.Table7FieldEnum.EVA_CAMPO;
import static com.ayesa.batch.mappers.fields.Table7FieldEnum.SUBS_RIESGO_ELECTRICO;
import static com.ayesa.batch.mappers.fields.Table7FieldEnum.TIP_ELE_RIESGOELEC;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.CANT_ATENCIONES;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ALIMENTADOR;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_SED;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_SIST_ELECTRICO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COORD_UTM_X;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COORD_UTM_Y;

public class Table7Mapper extends AbstractEntityMapper {
    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, COD_SIST_ELECTRICO);
        getValueByType(entity, resultSet, COD_ALIMENTADOR);
        getValueByType(entity, resultSet, COD_SED);
        getValueByType(entity, resultSet, TIP_ELE_RIESGOELEC);
        getValueByType(entity, resultSet, COD_ELE_RIESGOELEC);
        getValueByType(entity, resultSet, EVA_CAMPO);
        getValueByType(entity, resultSet, CAUSA_RIESGO);
        getValueByType(entity, resultSet, DESC_MED_PREVENTIVAS);
        getValueByType(entity, resultSet, SUBS_RIESGO_ELECTRICO);
        getValueByType(entity, resultSet, CAUSA_NO_CUMPLIMIENTO);
        getValueByType(entity, resultSet, COORD_UTM_X);
        getValueByType(entity, resultSet, COORD_UTM_Y);
        getValueByType(entity, resultSet, CANT_ATENCIONES);
        return entity;
    }

    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        throw new UnsupportedOperationException(" toListDTO - Operacion no soportada");
    }
}
