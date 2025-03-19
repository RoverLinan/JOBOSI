package com.ayesa.batch.business.dto.osinergmin;

import java.io.Serializable;

public class TableStructureDTO implements Serializable {

    private String codigoCampo;
    private String nroCampo;
    private String tipoDato;
    private String descripcionCampo;
    private String esObligatorio;


    public String getCodigoCampo() {
        return codigoCampo;
    }

    public void setCodigoCampo(String codigoCampo) {
        this.codigoCampo = codigoCampo;
    }

    public String getNroCampo() {
        return nroCampo;
    }

    public void setNroCampo(String nroCampo) {
        this.nroCampo = nroCampo;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getDescripcionCampo() {
        return descripcionCampo;
    }

    public void setDescripcionCampo(String descripcionCampo) {
        this.descripcionCampo = descripcionCampo;
    }

    public String getEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(String esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    @Override
    public String toString() {
        return "TableStructureDTO{" +
                "codigoCampo='" + codigoCampo + '\'' +
                ", nroCampo='" + nroCampo + '\'' +
                ", tipoDato='" + tipoDato + '\'' +
                ", descripcionCampo='" + descripcionCampo + '\'' +
                ", esObligatorio='" + esObligatorio + '\'' +
                '}';
    }
}
