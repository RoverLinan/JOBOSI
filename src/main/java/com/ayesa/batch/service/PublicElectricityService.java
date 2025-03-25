package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;

public interface PublicElectricityService {

    TableCatalogResponseDTO getTableCatalog();
    TableStructureResponseDTO getTableStructure(String tableCode);
    AbstractResponseDTO submitInformationForProcessing(final String filePath);
    AbstractResponseDTO confirmInformationSubmission();
    AbstractResponseDTO revertInformationConfirmation();

}
