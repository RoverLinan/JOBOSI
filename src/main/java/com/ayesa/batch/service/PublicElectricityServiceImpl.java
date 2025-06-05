package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;
import com.ayesa.batch.enums.HttpMethodEnum;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import com.ayesa.batch.mappers.Table1Mapper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

import static com.ayesa.batch.BatchLauncher.JOB_PARAMETERS;
import static com.ayesa.batch.enums.JobParameterEnum.URL_ATEN;
import static com.ayesa.batch.enums.JobParameterEnum.URL_CONFI;
import static com.ayesa.batch.enums.JobParameterEnum.URL_REMI;
import static com.ayesa.batch.enums.JobParameterEnum.URL_REVER;

public class PublicElectricityServiceImpl extends PublicElectricityService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PublicElectricityServiceImpl.class);
    private final JobNameEnum jobNameEnum;

    public PublicElectricityServiceImpl(JobNameEnum jobNameEnum) {
        this.jobNameEnum = jobNameEnum;
    }


    @Override
    public TableCatalogResponseDTO getTableCatalog() {
        return null;
    }

    @Override
    public TableStructureResponseDTO getTableStructure(String tableCode) {
        return null;
    }

    @Override
    public AbstractResponseDTO submitAttentionRegister(AttentionRegisterRequestDTO attentionRegisterRequestDTO) {
        AbstractResponseDTO abstractResponseDTO;
        try  {

            LOGGER.info("submitInformationForProcessing: request dto = {}", attentionRegisterRequestDTO);
            LOGGER.info("submitInformationForProcessing: request usuario = {}", usuario);

            MultipartBody.Builder builder = Table1Mapper.mapToRequestMultipartBody(attentionRegisterRequestDTO);
            builder.addFormDataPart("usuario", usuario);
            builder.addFormDataPart("clave", clave);

            Response response = executeRequest(builder.build(), URL_ATEN, HttpMethodEnum.POST);
            abstractResponseDTO = processResponse(response);
            LOGGER.info("submitInformationForProcessing: response = {}", abstractResponseDTO);
            return abstractResponseDTO;
        }catch (Exception e) {
            LOGGER.error("Error al registrar la atencion", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractResponseDTO submitInformationForProcessing(final String filePath) {
        AbstractResponseDTO abstractResponseDTO;
        try {


            final String codigoTabla = jobNameEnum.getTableName();
            final String delimitador = (String) JOB_PARAMETERS.get(JobParameterEnum.CHAR_DELIM.name());
            LOGGER.info("submitInformationForProcessing: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("submitInformationForProcessing: request codigoTabla = {}", codigoTabla);
            LOGGER.info("submitInformationForProcessing: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("submitInformationForProcessing: request delimitador = {}", delimitador);
            LOGGER.info("submitInformationForProcessing: request usuario = {}", usuario);


            File file = new File(filePath);
            MultipartBody.Builder builder = createCommonOkHttpRequest();
            builder.addFormDataPart("codigoTabla", codigoTabla);
            builder.addFormDataPart("delimitador", delimitador);
            builder.addFormDataPart("informacionARemitir", file.getName(),
                    RequestBody.create(MediaType.parse("application/octet-stream"),
                            file));

            Response response = executeRequest(builder.build(), URL_REMI, HttpMethodEnum.POST);

            abstractResponseDTO = processResponse(response);

            LOGGER.info("submitInformationForProcessing: response = {}", abstractResponseDTO);
        } catch (Exception e) {
            LOGGER.error("Error al enviar la información para procesar", e);
            throw new RuntimeException(e);
        }
        return abstractResponseDTO;
    }

    @Override
    public AbstractResponseDTO confirmInformationSubmission() {
        AbstractResponseDTO abstractResponseDTO;
        try {

            LOGGER.info("confirmInformationSubmission: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("confirmInformationSubmission: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("confirmInformationSubmission: request usuario = {}", usuario);

            RequestBody body = createCommonOkHttpRequest().build();
            Response response = executeRequest(body, URL_CONFI, HttpMethodEnum.PUT);
            abstractResponseDTO = processResponse(response);

            LOGGER.info("confirmInformationSubmission: response = {}", abstractResponseDTO);
        } catch (Exception e) {
            LOGGER.error("Error al enviar la información para confirmar", e);
            throw new RuntimeException(e);
        }
        return abstractResponseDTO;
    }

    @Override
    public AbstractResponseDTO revertInformationConfirmation() {
        AbstractResponseDTO abstractResponseDTO;
        try {

            LOGGER.info("revertInformationConfirmation: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("revertInformationConfirmation: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("revertInformationConfirmation: request usuario = {}", usuario);


            RequestBody body = createCommonOkHttpRequest().build();
            Response response = executeRequest(body, URL_REVER, HttpMethodEnum.PUT);
            abstractResponseDTO = processResponse(response);

            LOGGER.info("revertInformationConfirmation: response = {}", abstractResponseDTO);
        } catch (Exception e) {
            LOGGER.error("Error al enviar la información para revertir", e);
            throw new RuntimeException(e);
        }
        return abstractResponseDTO;
    }




}
