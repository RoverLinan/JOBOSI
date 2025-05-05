package com.ayesa.batch.mappers;

import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.util.DateUtil;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.core5.http.ContentType;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.*;
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


        changeFormatDate(entity, FEC_CREACION, DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2);
        changeFormatDate(entity, FEC_SOLUCION, DateUtil.FORMAT_DATETIME_1, DateUtil.FORMAT_DATETIME_2);
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
        dto.setCodigoEmpresa(       (String)data.get(COD_EMPRESA.getFieldName()));
        dto.setCodigoAtencion(      (String)data.get(COD_ATENCION.getFieldName()));
        dto.setFechaHoraRecepcion(  (String)data.get(FEC_CREACION.getFieldName()));
        dto.setCanalRecepcion(      Integer.parseInt(String.valueOf(data.get(COD_CANAL.getFieldName()))));
        dto.setTipoDocumento(       Integer.parseInt(String.valueOf(data.get(COD_TIP_DOCUMENTO.getFieldName()))));
        dto.setNumeroDocumento(     (String)data.get(NRO_DOCTO_IDENT.getFieldName()));
        dto.setNombres(             (String)data.get(NOMBRE_RAZON_SOCIAL.getFieldName()));
        dto.setApellidos(           (String)data.get(APELLIDO_SOLICITANTE.getFieldName()));
        dto.setNumeroSuministro(    (String)data.get(NUM_SUMINISTRO.getFieldName()));
        dto.setCorreoElectronico(   (String)data.get(EMAIL_SOLICITANTE.getFieldName()));
        dto.setTelefonos(           (String)data.get(TELEF_SOLICITANTE.getFieldName()));
        dto.setDireccion(           (String)data.get(DIRECCION.getFieldName()));
        dto.setUbigeo(              (String)data.get(UBIGEO.getFieldName()));
        dto.setCodigoAsunto(        Integer.parseInt(String.valueOf(data.get(COD_ASUNTO.getFieldName()))));
        dto.setFechaHoraSolucion(   (String)data.get(FEC_SOLUCION.getFieldName()));
        dto.setDescripcion(         (String)data.get(DESCRIPCION_RECLAMO.getFieldName()));


        return dto;
    }


    public static MultipartEntityBuilder mapToRequestMultipart(AttentionRegisterRequestDTO attentionRegisterRequestDTO) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("codigoEmpresa", new StringBody(attentionRegisterRequestDTO.getCodigoEmpresa(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("codigoAtencion", new StringBody(attentionRegisterRequestDTO.getCodigoAtencion(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("fechaHoraRecepcion", new StringBody(attentionRegisterRequestDTO.getFechaHoraRecepcion(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("canalRecepcion", new StringBody(String.valueOf(attentionRegisterRequestDTO.getCanalRecepcion()), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("tipoDocumento", new StringBody(String.valueOf(attentionRegisterRequestDTO.getTipoDocumento()), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("numeroDocumento", new StringBody(attentionRegisterRequestDTO.getNumeroDocumento(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("nombres", new StringBody(attentionRegisterRequestDTO.getNombres(), ContentType.APPLICATION_FORM_URLENCODED));
        if(Objects.nonNull(attentionRegisterRequestDTO.getApellidos())){
            builder.addPart("apellidos", new StringBody(attentionRegisterRequestDTO.getApellidos(), ContentType.APPLICATION_FORM_URLENCODED));
        }else{
            builder.addPart("apellidos", new StringBody("", ContentType.APPLICATION_FORM_URLENCODED));
        }

        if(Objects.nonNull(attentionRegisterRequestDTO.getNumeroSuministro())){
            builder.addPart("numeroSuministro", new StringBody(attentionRegisterRequestDTO.getNumeroSuministro(), ContentType.APPLICATION_FORM_URLENCODED));
        }else {
            builder.addPart("numeroSuministro", new StringBody("", ContentType.APPLICATION_FORM_URLENCODED));
        }

        if (Objects.nonNull(attentionRegisterRequestDTO.getCorreoElectronico())){
            builder.addPart("correoElectronico", new StringBody(attentionRegisterRequestDTO.getCorreoElectronico(), ContentType.APPLICATION_FORM_URLENCODED));
        }else {
            builder.addPart("correoElectronico", new StringBody("", ContentType.APPLICATION_FORM_URLENCODED));
        }

        if(Objects.nonNull(attentionRegisterRequestDTO.getTelefonos())){
            builder.addPart("telefonos", new StringBody(attentionRegisterRequestDTO.getTelefonos(), ContentType.APPLICATION_FORM_URLENCODED));
        }else {
            builder.addPart("telefonos", new StringBody("", ContentType.APPLICATION_FORM_URLENCODED));
        }

        builder.addPart("direccion", new StringBody(attentionRegisterRequestDTO.getDireccion(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("ubigeo", new StringBody(attentionRegisterRequestDTO.getUbigeo(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("codigoAsunto", new StringBody(String.valueOf(attentionRegisterRequestDTO.getCodigoAsunto()), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("fechaHoraSolucion", new StringBody(attentionRegisterRequestDTO.getFechaHoraSolucion(), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("descripcion", new StringBody(attentionRegisterRequestDTO.getDescripcion(), ContentType.APPLICATION_FORM_URLENCODED));

        return builder;
    }

}
