package com.ayesa.batch.business.dto.osinergmin;

import java.util.List;

public class TableCatalogResponseDTO extends AbstractResponseDTO{
    private List<TableCatalogDTO> listaTablas;


    public List<TableCatalogDTO> getListaTablas() {
        return listaTablas;
    }

    public void setListaTablas(List<TableCatalogDTO> listaTablas) {
        this.listaTablas = listaTablas;
    }

    @Override
    public String toString() {
        return "TableCatalogResponseDTO{" +
                "listaTablas=" + listaTablas +
                "} " + super.toString();
    }
}
