package com.ayesa.batch.business.dto.osinergmin;

import java.io.Serializable;

public class TableCatalogDTO implements Serializable {

    private String codigoTabla;
    private String descripcionTabla;
    private String nombreCorto;


    public String getCodigoTabla() {
        return codigoTabla;
    }

    public void setCodigoTabla(String codigoTabla) {
        this.codigoTabla = codigoTabla;
    }

    public String getDescripcionTabla() {
        return descripcionTabla;
    }

    public void setDescripcionTabla(String descripcionTabla) {
        this.descripcionTabla = descripcionTabla;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TableCatalogDTO{");
        sb.append("codigoTabla='").append(codigoTabla).append('\'');
        sb.append(", descripcionTabla='").append(descripcionTabla).append('\'');
        sb.append(", nombreCorto='").append(nombreCorto).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
