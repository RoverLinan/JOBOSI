package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
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

import java.io.File;

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
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost post = createHttpPost(URL_ATEN);
            LOGGER.info("submitInformationForProcessing: request dto = {}", attentionRegisterRequestDTO);
            LOGGER.info("submitInformationForProcessing: request usuario = {}", usuario);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("codigoEmpresa", new StringBody(attentionRegisterRequestDTO.getCodigoEmpresa(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("codigoAtencion", new StringBody(attentionRegisterRequestDTO.getCodigoAtencion(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("fechaHoraRecepcion", new StringBody(attentionRegisterRequestDTO.getFechaHoraRecepcion(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("canalRecepcion", new StringBody(String.valueOf(attentionRegisterRequestDTO.getCanalRecepcion()), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("tipoDocumento", new StringBody(String.valueOf(attentionRegisterRequestDTO.getTipoDocumento()), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("numeroDocumento", new StringBody(attentionRegisterRequestDTO.getNumeroDocumento(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("nombres", new StringBody(attentionRegisterRequestDTO.getNombres(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("apellidos", new StringBody(attentionRegisterRequestDTO.getApellidos(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("numeroSuministro", new StringBody(attentionRegisterRequestDTO.getNumeroSuministro(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("correoElectronico", new StringBody(attentionRegisterRequestDTO.getCorreoElectronico(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("telefonos", new StringBody(attentionRegisterRequestDTO.getTelefonos(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("direccion", new StringBody(attentionRegisterRequestDTO.getDireccion(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("ubigeo", new StringBody(attentionRegisterRequestDTO.getUbigeo(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("codigoAsunto", new StringBody(String.valueOf(attentionRegisterRequestDTO.getCodigoAsunto()), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("fechaHoraSolucion", new StringBody(attentionRegisterRequestDTO.getFechaHoraSolucion(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("descripcion", new StringBody(attentionRegisterRequestDTO.getDescripcion(), ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("usuario", new StringBody(usuario, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("clave", new StringBody(clave, ContentType.APPLICATION_FORM_URLENCODED));

            HttpEntity entity = builder.build();
            post.setEntity(entity);

            CloseableHttpResponse response = client.execute(post);
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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = createHttpPost(URL_REMI);

            final String codigoTabla = jobNameEnum.getTableName();
            final String delimitador = (String) JOB_PARAMETERS.get(JobParameterEnum.CHAR_DELIM.name());
            LOGGER.info("submitInformationForProcessing: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("submitInformationForProcessing: request codigoTabla = {}", codigoTabla);
            LOGGER.info("submitInformationForProcessing: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("submitInformationForProcessing: request delimitador = {}", delimitador);
            LOGGER.info("submitInformationForProcessing: request usuario = {}", usuario);

            MultipartEntityBuilder builder = createCommonMultipartEntityBuilder();
            builder.addPart("codigoTabla", new StringBody(codigoTabla, ContentType.APPLICATION_FORM_URLENCODED));
            builder.addPart("delimitador", new StringBody(delimitador, ContentType.APPLICATION_FORM_URLENCODED));

            File file = new File(filePath);
            builder.addPart("informacionARemitir", new FileBody(file));
            LOGGER.info("submitInformationForProcessing: request filePath = {}", filePath);


            HttpEntity entity = builder.build();
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut put = createHttpPut(URL_CONFI);

            LOGGER.info("confirmInformationSubmission: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("confirmInformationSubmission: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("confirmInformationSubmission: request usuario = {}", usuario);

            HttpEntity entity = createCommonMultipartEntityBuilder().build();
            put.setEntity(entity);
            CloseableHttpResponse response = client.execute(put);
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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut put = createHttpPut(URL_REVER);

            LOGGER.info("revertInformationConfirmation: request codigoPeriodoRemision = {}", codigoPeriodoRemision);
            LOGGER.info("revertInformationConfirmation: request codigoEmpresa = {}", codigoEmpresa);
            LOGGER.info("revertInformationConfirmation: request usuario = {}", usuario);

            HttpEntity entity = createCommonMultipartEntityBuilder().build();
            put.setEntity(entity);
            CloseableHttpResponse response = client.execute(put);
            abstractResponseDTO = processResponse(response);

            LOGGER.info("revertInformationConfirmation: response = {}", abstractResponseDTO);
        } catch (Exception e) {
            LOGGER.error("Error al enviar la información para revertir", e);
            throw new RuntimeException(e);
        }
        return abstractResponseDTO;
    }




}
