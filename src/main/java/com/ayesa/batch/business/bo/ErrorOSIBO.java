package com.ayesa.batch.business.bo;

import com.ayesa.batch.enums.StatusEnum;
import com.ayesa.batch.util.DateUtil;

import java.util.UUID;

public class ErrorOSIBO {
    private String uuid;
    private String nombreTabla;
    private String codAccion;
    private String codAtencion;
    private String codError;
    private String mensajeError;
    private String tipoError;
    private String campoError;
    private String codigoErrorExterno;
    private String estadoError;
    private String comentariosAdicionales;
    private String usuario;
    private String fechaError;


    public ErrorOSIBO(String nombreTabla, String codAtencion, String tipoError) {
        this.uuid = UUID.randomUUID().toString();
        this.nombreTabla = nombreTabla;
        this.codAtencion = codAtencion;
        this.tipoError = tipoError;
        this.estadoError = StatusEnum.PENDIENTE.name();
        this.fechaError = DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getCodAccion() {
        return codAccion;
    }

    public void setCodAccion(String codAccion) {
        this.codAccion = codAccion;
    }

    public String getCodAtencion() {
        return codAtencion;
    }

    public void setCodAtencion(String codAtencion) {
        this.codAtencion = codAtencion;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public String getCampoError() {
        return campoError;
    }

    public void setCampoError(String campoError) {
        this.campoError = campoError;
    }

    public String getCodigoErrorExterno() {
        return codigoErrorExterno;
    }

    public void setCodigoErrorExterno(String codigoErrorExterno) {
        this.codigoErrorExterno = codigoErrorExterno;
    }

    public String getEstadoError() {
        return estadoError;
    }

    public void setEstadoError(String estadoError) {
        this.estadoError = estadoError;
    }

    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaError() {
        return fechaError;
    }

    public void setFechaError(String fechaError) {
        this.fechaError = fechaError;
    }
}
