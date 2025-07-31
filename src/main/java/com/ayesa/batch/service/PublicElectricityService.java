package com.ayesa.batch.service;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;
import com.ayesa.batch.enums.HttpMethodEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import com.ayesa.batch.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;
import java.time.LocalDate;

import static com.ayesa.batch.BatchLauncher.JOB_PARAMETERS;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.URL_OSI;

public abstract class PublicElectricityService {

     String codigoPeriodoRemision = DateUtil.parseDateToPeriod((LocalDate) JOB_PARAMETERS.get(PERIODO_REMISION.name()));
     String codigoEmpresa = (String)JOB_PARAMETERS.get(JobParameterEnum.CODEMP.name());
     String usuario = (String)JOB_PARAMETERS.get(JobParameterEnum.OSI_USER.name());
     String clave = (String)JOB_PARAMETERS.get(JobParameterEnum.OSI_PASS.name());

    public abstract TableCatalogResponseDTO getTableCatalog();
    public abstract TableStructureResponseDTO getTableStructure(String tableCode);

    public abstract AbstractResponseDTO submitAttentionRegister(AttentionRegisterRequestDTO attentionRegisterRequestDTO);
    public abstract AbstractResponseDTO submitInformationForProcessing(final String filePath);
    public abstract AbstractResponseDTO confirmInformationSubmission();
    public abstract AbstractResponseDTO revertInformationConfirmation();

    protected HttpPost createHttpPost(JobParameterEnum parameter) {
        return new HttpPost(getURL(parameter));
    }

    protected HttpPut createHttpPut(JobParameterEnum parameter) {
        return new HttpPut(getURL(parameter));
    }

    protected   String getURL(JobParameterEnum parameter) {
        final StringBuilder URL = new StringBuilder((String) BatchLauncher.JOB_PARAMETERS.get( URL_OSI.name()));
        URL.append((String)BatchLauncher.JOB_PARAMETERS.get( parameter.name()));
        return URL.toString();
    }

    protected MultipartEntityBuilder createCommonMultipartEntityBuilder() {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("codigoPeriodoRemision", new StringBody(codigoPeriodoRemision, ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("codigoEmpresa", new StringBody(codigoEmpresa, ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("usuario", new StringBody(usuario, ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart("clave", new StringBody(clave, ContentType.APPLICATION_FORM_URLENCODED));
        return builder;
    }

    protected MultipartBody.Builder createCommonOkHttpRequest() {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("codigoPeriodoRemision", codigoPeriodoRemision)
                .addFormDataPart("codigoEmpresa", codigoEmpresa)
                .addFormDataPart("usuario", usuario)
                .addFormDataPart("clave", clave);
    }

    protected Response executeRequest(RequestBody body, JobParameterEnum parameter, HttpMethodEnum httpMethod) throws IOException {
        Request request = new Request.Builder()
                .url(getURL(parameter))
                .method(httpMethod.toString(), body)
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        return client.newCall(request).execute();
    }

    protected AbstractResponseDTO processResponse(Response response) throws IOException {

        if (response.isSuccessful()) {
            if(response.body() == null) {
                throw new RuntimeException("Response body is null");
            }
            String responseString = response.body().string();
            responseString = responseString.replace("\\r", " "); // Normalize line endings
            return new ObjectMapper().readValue(responseString, AbstractResponseDTO.class);
        }else {
            throw new RuntimeException("Error in response: " + response.code() + " - " + response.message());
        }
    }

}
