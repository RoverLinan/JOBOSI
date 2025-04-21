package com.ayesa.batch.mappers;

import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.util.DateUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ayesa.batch.mappers.fields.Table1FieldEnum.APELLIDO_SOLICITANTE;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_ASUNTO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_CANAL;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_EMPRESA;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.COD_TIP_DOCUMENTO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.DESCRIPCION_RECLAMO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.DIRECCION;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.EMAIL_SOLICITANTE;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.FEC_SOLUCION;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.NOMBRE_RAZON_SOCIAL;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.NRO_DOCTO_IDENT;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.NUM_SUMINISTRO;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.TELEF_SOLICITANTE;
import static com.ayesa.batch.mappers.fields.Table1FieldEnum.UBIGEO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_DISTRITO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ESTADO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.FEC_CREACION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.TIP_ATENCION;


public class Table1Mapper extends AbstractEntityMapper{


    @Override
    public Map<String, Object> toEntity(ResultSet resultSet) {

        Map<String, Object> entity = new LinkedHashMap<>();

        getValueByType(entity, resultSet, COD_EMPRESA);
        getValueByType(entity, resultSet, COD_ATENCION);
        getValueByType(entity, resultSet, FEC_CREACION);
        getValueByType(entity, resultSet, COD_CANAL);
        getValueByType(entity, resultSet, COD_TIP_DOCUMENTO);
        getValueByType(entity, resultSet, NRO_DOCTO_IDENT);
        getValueByType(entity, resultSet, NOMBRE_RAZON_SOCIAL);
        getValueByType(entity, resultSet, APELLIDO_SOLICITANTE);
        getValueByType(entity, resultSet, NUM_SUMINISTRO);
        getValueByType(entity, resultSet, EMAIL_SOLICITANTE);
        getValueByType(entity, resultSet, TELEF_SOLICITANTE);
        getValueByType(entity, resultSet, DIRECCION);
        getValueByType(entity, resultSet, UBIGEO);
        getValueByType(entity, resultSet, COD_ASUNTO);
        getValueByType(entity, resultSet, FEC_SOLUCION);
        getValueByType(entity, resultSet, DESCRIPCION_RECLAMO);
        getValueByType(entity, resultSet, COD_ESTADO);
        getValueByType(entity, resultSet, TIP_ATENCION);
        getValueByType(entity, resultSet, COD_DISTRITO);

        return entity;
    }

    @Override
    public List<Serializable> toListDTO(List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }
        return data.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private AttentionRegisterRequestDTO toDTO(Map<String, Object> data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        AttentionRegisterRequestDTO dto = new AttentionRegisterRequestDTO();
        dto.setCodigoEmpresa(       String.valueOf(data.get(COD_EMPRESA.getFieldName())));
        dto.setCodigoAtencion(      String.valueOf(data.get(COD_ATENCION.getFieldName())));
        dto.setFechaHoraRecepcion(  DateUtil.formatDateTime( String.valueOf(data.get(FEC_CREACION.getFieldName())),DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2 ));
        dto.setCanalRecepcion(      Integer.parseInt(String.valueOf(data.get(COD_CANAL.getFieldName()))));
        dto.setTipoDocumento(       Integer.parseInt(String.valueOf(data.get(COD_TIP_DOCUMENTO.getFieldName()))));
        dto.setNumeroDocumento(     String.valueOf(data.get(NRO_DOCTO_IDENT.getFieldName())));
        dto.setNombres(             String.valueOf(data.get(NOMBRE_RAZON_SOCIAL.getFieldName())));
        dto.setApellidos(           String.valueOf(data.get(APELLIDO_SOLICITANTE.getFieldName())));
        dto.setNumeroSuministro(    String.valueOf(data.get(NUM_SUMINISTRO.getFieldName())));
        dto.setCorreoElectronico(   String.valueOf(data.get(EMAIL_SOLICITANTE.getFieldName())));
        dto.setTelefonos(           String.valueOf(data.get(TELEF_SOLICITANTE.getFieldName())));
        dto.setDireccion(           String.valueOf(data.get(DIRECCION.getFieldName())));
        dto.setUbigeo(              String.valueOf(data.get(UBIGEO.getFieldName())));
        dto.setCodigoAsunto(        Integer.parseInt(String.valueOf(data.get(COD_ASUNTO.getFieldName()))));
        dto.setFechaHoraSolucion(   DateUtil.formatDateTime( String.valueOf(data.get(FEC_SOLUCION.getFieldName())),DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2 ));
        dto.setDescripcion(         String.valueOf(data.get(DESCRIPCION_RECLAMO.getFieldName())));
        return dto;
    }

}
