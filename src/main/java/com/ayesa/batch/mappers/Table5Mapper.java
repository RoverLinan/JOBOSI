package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
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

        entity.put(COD_ATENCION.name(), "1");
        entity.put(COD_ACCION.name(), "2");
        entity.put(COD_SED.name(), "3");
        entity.put(COD_IRREGULARIDAD.name(), "4");
        entity.put(ZON_GEOGRAFICA.name(), "5");
        entity.put(CANT_SUM_AFECTADOS.name(), "6");
        entity.put(MES_FAC_IRREGULARIDAD.name(), "7");
        entity.put(MED_CORRECTIVA.name(), "8");
        entity.put(SUST_TECNICO.name(), "9");
        entity.put(CANT_ATENCIONES.name(), "10");

        return entity;
    }
}
