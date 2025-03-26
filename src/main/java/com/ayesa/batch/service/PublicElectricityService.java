package com.ayesa.batch.service;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;

import static com.ayesa.batch.enums.JobParameterEnum.URL_OSI;
import static com.ayesa.batch.enums.JobParameterEnum.URL_REMI;

public interface PublicElectricityService {

    TableCatalogResponseDTO getTableCatalog();
    TableStructureResponseDTO getTableStructure(String tableCode);
    AbstractResponseDTO submitInformationForProcessing(final String filePath, JobNameEnum jobName);
    AbstractResponseDTO confirmInformationSubmission();
    AbstractResponseDTO revertInformationConfirmation();

    default HttpPost createHttpPost(JobParameterEnum parameter) {
        return new HttpPost(getURL(parameter));
    }

    default HttpPut createHttpPut(JobParameterEnum parameter) {
        return new HttpPut(getURL(parameter));
    }

     default  String getURL(JobParameterEnum parameter) {
        final StringBuilder URL = new StringBuilder((String) BatchLauncher.JOB_PARAMETERS.get( URL_OSI.name()));
        URL.append((String)BatchLauncher.JOB_PARAMETERS.get( parameter.name()));
        return URL.toString();
    }

}
