package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.ayesa.batch.BatchLauncher.JOB_PARAMETERS;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.URL_REMI;

public class PublicElectricityServiceImpl implements PublicElectricityService{


    private static final Logger LOGGER = LoggerFactory.getLogger(PublicElectricityServiceImpl.class);


    @Override
    public TableCatalogResponseDTO getTableCatalog() {
        return null;
    }

    @Override
    public TableStructureResponseDTO getTableStructure(String tableCode) {
        return null;
    }

    @Override
    public AbstractResponseDTO submitInformationForProcessing(final String filePath, JobNameEnum jobName) {
        AbstractResponseDTO abstractResponseDTO;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = createHttpPost(URL_REMI);

            final String codigoPeriodoRemision = (String)JOB_PARAMETERS.get(PERIODO_REMISION.name());
            final String codigoTabla = jobName.getTableName();
            final String codigoEmpresa = (String)JOB_PARAMETERS.get(JobParameterEnum.CODEMP.name());
            final String delimitador = (String)JOB_PARAMETERS.get(JobParameterEnum.CHAR_DELIM.name());
            final String usuario = (String)JOB_PARAMETERS.get(JobParameterEnum.OSI_USER.name());
            final String clave = (String)JOB_PARAMETERS.get(JobParameterEnum.OSI_PASS.name());
            LOGGER.info("submitInformationForProcessing: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("submitInformationForProcessing: request codigoTabla = {}", codigoTabla);
            LOGGER.info("submitInformationForProcessing: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("submitInformationForProcessing: request delimitador = {}", delimitador);
            LOGGER.info("submitInformationForProcessing: request usuario = {}", usuario);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("codigoPeriodoRemision", new StringBody(codigoPeriodoRemision, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("codigoTabla", new StringBody(codigoTabla, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("codigoEmpresa", new StringBody(codigoEmpresa, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("delimitador", new StringBody(delimitador, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("usuario", new StringBody(usuario, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("clave", new StringBody(clave, ContentType.APPLICATION_FORM_URLENCODED));

            File file = new File(filePath);
            builder.addPart("informacionARemitir", new FileBody(file));
            LOGGER.info("submitInformationForProcessing: request filePath = {}", filePath);


            HttpEntity entity = builder.build();
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            String responseString = EntityUtils.toString (response.getEntity());
            abstractResponseDTO = new ObjectMapper().readValue(responseString, AbstractResponseDTO.class);


            LOGGER.info("submitInformationForProcessing: response = {}", abstractResponseDTO);
        } catch (Exception e) {
            LOGGER.error("Error al enviar la información para procesar", e);
            throw new RuntimeException(e);
        }
        return abstractResponseDTO;
    }

    @Override
    public AbstractResponseDTO confirmInformationSubmission() {
        return null;
    }

    @Override
    public AbstractResponseDTO revertInformationConfirmation() {
        return null;
    }


}
