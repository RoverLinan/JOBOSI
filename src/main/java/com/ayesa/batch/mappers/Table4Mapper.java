package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ayesa.batch.mappers.fields.Table4FieldEnum.CANT_USU_AFECTADOS;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.COD_CAUSA_VAR_TENSION;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.COD_MED_ATENCION;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.COD_TIP_CONEX;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.FEC_ULT_APLICACION;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.SUM_AFECTADOS;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.SUST_TEC_CONCLUYE;
import static com.ayesa.batch.mappers.fields.Table4FieldEnum.SUST_TEC_ORIGEN;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.CANT_ATENCIONES;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ALIMENTADOR;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_SED;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COORD_UTM_X;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COORD_UTM_Y;

public class Table4Mapper extends AbstractEntityMapper{


    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, COD_ALIMENTADOR);
        getValueByType(entity, resultSet, COD_SED);
        getValueByType(entity, resultSet, COD_TIP_CONEX);
        getValueByType(entity, resultSet, CANT_USU_AFECTADOS);
        getValueByType(entity, resultSet, SUM_AFECTADOS);
        getValueByType(entity, resultSet, COD_CAUSA_VAR_TENSION);
        getValueByType(entity, resultSet, SUST_TEC_ORIGEN);
        getValueByType(entity, resultSet, COD_MED_ATENCION);
        getValueByType(entity, resultSet, FEC_ULT_APLICACION);
        getValueByType(entity, resultSet, SUST_TEC_CONCLUYE);
        getValueByType(entity, resultSet, COORD_UTM_X);
        getValueByType(entity, resultSet, COORD_UTM_Y);
        getValueByType(entity, resultSet, CANT_ATENCIONES);

        return entity;
    }
}
