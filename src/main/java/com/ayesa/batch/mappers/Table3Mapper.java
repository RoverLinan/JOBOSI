package com.ayesa.batch.mappers;

import com.ayesa.batch.util.DateUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ayesa.batch.mappers.fields.Table2FieldEnum.FEC_RESPUESTA;
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
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.FEC_CREACION;

public class Table3Mapper extends AbstractEntityMapper{


    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, COD_INTERRUPCION);
        getValueByType(entity, resultSet, FEC_INI_INTERRUPCION);
        getValueByType(entity, resultSet, FEC_FIN_INTERRUPCION);
        getValueByType(entity, resultSet, CANT_SUM_AFECTADOS);
        getValueByType(entity, resultSet, FASE_INTERRUMPIDA);
        getValueByType(entity, resultSet, POTENCIA_INTERRUMPIDA);
        getValueByType(entity, resultSet, ENERGIA_NO_SUMINISTRADA);
        getValueByType(entity, resultSet, COD_MOTIVO_FALLA);
        getValueByType(entity, resultSet, DESCRIPCION_MOTIVO);
        getValueByType(entity, resultSet, LOCALIZACION_FALLA);
        getValueByType(entity, resultSet, SUST_TEC_A_INTERR_4_12);
        getValueByType(entity, resultSet, SUST_TEC_A_INTERR_MAS_12);
        getValueByType(entity, resultSet, SUST_TEC_C);
        getValueByType(entity, resultSet, DESCR_ACC_REALIZADAS);
        getValueByType(entity, resultSet, CARACT_TEC_PUESTA_OPER);
        getValueByType(entity, resultSet, ACTA_INSPECCION);
        getValueByType(entity, resultSet, CANT_ATENCIONES);

        changeFormatDate(entity, FEC_INI_INTERRUPCION, DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2);
        changeFormatDate(entity, FEC_FIN_INTERRUPCION, DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2);
        return entity;
    }


    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        throw new UnsupportedOperationException(" toListDTO - Operacion no soportada");
    }
}
