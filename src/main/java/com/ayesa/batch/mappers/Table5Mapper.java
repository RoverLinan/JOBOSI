package com.ayesa.batch.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.ayesa.batch.mappers.fields.Table5FieldEnum.CANT_SUM_AFECTADOS;
import static com.ayesa.batch.mappers.fields.Table5FieldEnum.COD_IRREGULARIDAD;
import static com.ayesa.batch.mappers.fields.Table5FieldEnum.MED_CORRECTIVA;
import static com.ayesa.batch.mappers.fields.Table5FieldEnum.MES_FAC_IRREGULARIDAD;
import static com.ayesa.batch.mappers.fields.Table5FieldEnum.SUST_TECNICO;
import static com.ayesa.batch.mappers.fields.Table5FieldEnum.ZON_GEOGRAFICA;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.CANT_ATENCIONES;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_SED;

public class Table5Mapper extends AbstractEntityMapper{
    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {
        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, COD_ACCION);
        getValueByType(entity, resultSet, COD_SED);
        getValueByType(entity, resultSet, COD_IRREGULARIDAD);
        getValueByType(entity, resultSet, ZON_GEOGRAFICA);
        getValueByType(entity, resultSet, CANT_SUM_AFECTADOS);
        getValueByType(entity, resultSet, MES_FAC_IRREGULARIDAD);
        getValueByType(entity, resultSet, MED_CORRECTIVA);
        getValueByType(entity, resultSet, SUST_TECNICO);
        getValueByType(entity, resultSet, CANT_ATENCIONES);

        return entity;
    }

    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        throw new UnsupportedOperationException(" toListDTO - Operacion no soportada");
    }
}
