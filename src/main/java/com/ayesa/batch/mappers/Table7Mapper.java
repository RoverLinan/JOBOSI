package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
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
        entity.put(COD_ATENCION.name(), "1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(COD_SIST_ELECTRICO.name(), "3");
        entity.put(COD_ALIMENTADOR.name(), "4");
        entity.put(COD_SED.name(), "5");
        entity.put(TIP_ELE_RIESGOELEC.name(), "6");
        entity.put(COD_ELE_RIESGOELEC.name(), "7");
        entity.put(EVA_CAMPO.name(), "8");
        entity.put(CAUSA_RIESGO.name(), "9");
        entity.put(DESC_MED_PREVENTIVAS.name(), "10");
        entity.put(SUBS_RIESGO_ELECTRICO.name(), "11");
        entity.put(CAUSA_NO_CUMPLIMIENTO.name(), "12");
        entity.put(COORD_UTM_X.name(), "13");
        entity.put(COORD_UTM_Y.name(), "14");
        entity.put(CANT_ATENCIONES.name(), "15");
        return entity;
    }
}
