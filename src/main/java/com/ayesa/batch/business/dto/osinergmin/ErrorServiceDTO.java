package com.ayesa.batch.business.dto.osinergmin;

import java.io.Serializable;

public class ErrorServiceDTO implements Serializable {
    private String codigo;
    private String descripcion;
    private String linea;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }


    @Override
    public String toString() {
        return "ErrorServiceDTO{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", linea='" + linea + '\'' +
                '}';
    }
}
