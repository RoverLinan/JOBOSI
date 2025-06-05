package com.ayesa.batch.business.dto.osinergmin;

import java.io.Serializable;
import java.util.List;

public  class AbstractResponseDTO implements Serializable {
    public AbstractResponseDTO() {
    }
    private List<ErrorServiceDTO> listaErrores;
    private int valorResultante;
    private String codigoMensaje;
    private String mensajeResultante;

    public List<ErrorServiceDTO> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<ErrorServiceDTO> listaErrores) {
        this.listaErrores = listaErrores;
    }

    public int getValorResultante() {
        return valorResultante;
    }

    public void setValorResultante(int valorResultante) {
        this.valorResultante = valorResultante;
    }

    public String getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getMensajeResultante() {
        return mensajeResultante;
    }

    public void setMensajeResultante(String mensajeResultante) {
        this.mensajeResultante = mensajeResultante;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractResponseDTO{");
        sb.append("listaErrores=").append(listaErrores);
        sb.append(", valorResultante=").append(valorResultante);
        sb.append(", codigoMensaje='").append(codigoMensaje).append('\'');
        sb.append(", mensajeResultante='").append(mensajeResultante).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
