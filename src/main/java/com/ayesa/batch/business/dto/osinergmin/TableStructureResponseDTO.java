package com.ayesa.batch.business.dto.osinergmin;

import java.util.List;

public class TableStructureResponseDTO extends AbstractResponseDTO{

    private List<TableStructureDTO> listaCampos;

    public List<TableStructureDTO> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<TableStructureDTO> listaCampos) {
        this.listaCampos = listaCampos;
    }

    @Override
    public String toString() {
        return "TableStructureResponseDTO{" +
                "listaCampos=" + listaCampos +
                "} " + super.toString();
    }
}
