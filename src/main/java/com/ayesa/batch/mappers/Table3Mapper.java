package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ayesa.batch.mappers.fields.Table3FieldEnum.ACTA_INSPECCION;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.CANT_SUM_AFECTADOS;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.CARACT_TEC_PUESTA_OPER;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.COD_INTERRUPCION;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.COD_MOTIVO_FALLA;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.DESCRIPCION_MOTIVO;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.DESCR_ACC_REALIZADAS;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.ENERGIA_NO_SUMINISTRADA;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.FASE_INTERRUMPIDA;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.FEC_FIN_INTERRUPCION;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.FEC_INI_INTERRUPCION;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.LOCALIZACION_FALLA;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.POTENCIA_INTERRUMPIDA;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.SUST_TEC_A_INTERR_4_12;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.SUST_TEC_A_INTERR_MAS_12;
import static com.ayesa.batch.mappers.fields.Table3FieldEnum.SUST_TEC_C;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.CANT_ATENCIONES;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;

public class Table3Mapper extends AbstractEntityMapper{


    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();
        entity.put(COD_ATENCION.name(),"1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(COD_INTERRUPCION.name(), "3");
        entity.put(FEC_INI_INTERRUPCION.name(), "4");
        entity.put(FEC_FIN_INTERRUPCION.name(), "5");
        entity.put(CANT_SUM_AFECTADOS.name(), "6");
        entity.put(FASE_INTERRUMPIDA.name(), "7");
        entity.put(POTENCIA_INTERRUMPIDA.name(), "8");
        entity.put(ENERGIA_NO_SUMINISTRADA.name(), "9");
        entity.put(COD_MOTIVO_FALLA.name(), "10");
        entity.put(DESCRIPCION_MOTIVO.name(), "11");
        entity.put(LOCALIZACION_FALLA.name(), "12");
        entity.put(SUST_TEC_A_INTERR_4_12.name(), "13");
        entity.put(SUST_TEC_A_INTERR_MAS_12.name(), "14");
        entity.put(SUST_TEC_C.name(), "15");
        entity.put(DESCR_ACC_REALIZADAS.name(), "16");
        entity.put(CARACT_TEC_PUESTA_OPER.name(), "17");
        entity.put(ACTA_INSPECCION.name(), "18");
        entity.put(CANT_ATENCIONES.name(), "19");

        return entity;
    }
}
