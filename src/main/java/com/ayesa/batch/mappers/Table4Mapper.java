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
        entity.put(COD_ATENCION.name(),"1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(COD_ALIMENTADOR.name(), "3");
        entity.put(COD_SED.name(), "4");
        entity.put(COD_TIP_CONEX.name(), "5");
        entity.put(CANT_USU_AFECTADOS.name(), "6");
        entity.put(SUM_AFECTADOS.name(), "7");
        entity.put(COD_CAUSA_VAR_TENSION.name(), "8");
        entity.put(SUST_TEC_ORIGEN.name(), "9");
        entity.put(COD_MED_ATENCION.name(), "10");
        entity.put(FEC_ULT_APLICACION.name(), "11");
        entity.put(SUST_TEC_CONCLUYE.name(), "12");
        entity.put(COORD_UTM_X.name(), "13");
        entity.put(COORD_UTM_Y.name(), "14");
        entity.put(CANT_ATENCIONES.name(), "15");

        return entity;
    }
}
