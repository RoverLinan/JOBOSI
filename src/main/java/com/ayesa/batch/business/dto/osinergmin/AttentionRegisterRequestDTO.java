package com.ayesa.batch.business.dto.osinergmin;

import com.ayesa.batch.enums.StatusEnum;

import java.io.Serializable;

public class AttentionRegisterRequestDTO implements Serializable {

    private String codigoEmpresa;
    private String codigoAtencion;
    private String fechaHoraRecepcion;
    private int canalRecepcion;
    private int tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String numeroSuministro;
    private String correoElectronico;
    private String telefonos;
    private String direccion;
    private String ubigeo;
    private int codigoAsunto;
    private String fechaHoraSolucion;
    private String descripcion;

    private StatusEnum statusProcessing;


    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigoAtencion() {
        return codigoAtencion;
    }

    public void setCodigoAtencion(String codigoAtencion) {
        this.codigoAtencion = codigoAtencion;
    }

    public String getFechaHoraRecepcion() {
        return fechaHoraRecepcion;
    }

    public void setFechaHoraRecepcion(String fechaHoraRecepcion) {
        this.fechaHoraRecepcion = fechaHoraRecepcion;
    }

    public int getCanalRecepcion() {
        return canalRecepcion;
    }

    public void setCanalRecepcion(int canalRecepcion) {
        this.canalRecepcion = canalRecepcion;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroSuministro() {
        return numeroSuministro;
    }

    public void setNumeroSuministro(String numeroSuministro) {
        this.numeroSuministro = numeroSuministro;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public int getCodigoAsunto() {
        return codigoAsunto;
    }

    public void setCodigoAsunto(int codigoAsunto) {
        this.codigoAsunto = codigoAsunto;
    }

    public String getFechaHoraSolucion() {
        return fechaHoraSolucion;
    }

    public void setFechaHoraSolucion(String fechaHoraSolucion) {
        this.fechaHoraSolucion = fechaHoraSolucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public StatusEnum getStatusProcessing() {
        return statusProcessing;
    }

    public void setStatusProcessing(StatusEnum statusProcessing) {
        this.statusProcessing = statusProcessing;
    }

    @Override
    public String toString() {
        return "AttentionRegisterRequestDTO{" +
                "codigoEmpresa='" + codigoEmpresa + '\'' +
                ", codigoAtencion='" + codigoAtencion + '\'' +
                ", fechaHoraRecepcion='" + fechaHoraRecepcion + '\'' +
                ", canalRecepcion=" + canalRecepcion +
                ", tipoDocumento=" + tipoDocumento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", numeroSuministro='" + numeroSuministro + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefonos='" + telefonos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ubigeo='" + ubigeo + '\'' +
                ", codigoAsunto=" + codigoAsunto +
                ", fechaHoraSolucion='" + fechaHoraSolucion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", statusProcessing=" + statusProcessing +
                '}';
    }
}
