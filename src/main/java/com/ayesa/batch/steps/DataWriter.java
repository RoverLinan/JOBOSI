package com.ayesa.batch.steps;

import com.ayesa.batch.business.dto.notification.NotificationParameterDTO;
import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.StatusEnum;
import com.ayesa.batch.enums.error.ErrorTypeEnum;
import com.ayesa.batch.mappers.error.ErrorNotificationMapper;
import com.ayesa.batch.mappers.error.ErrorOSIMapper;
import com.ayesa.batch.repository.ErrorOSIRepository;
import com.ayesa.batch.repository.TableRepository;
import com.ayesa.batch.service.NotificationService;
import com.ayesa.batch.service.OutlookNotification;
import com.ayesa.batch.service.PublicElectricityService;
import com.ayesa.batch.service.PublicElectricityServiceImpl;
import com.ayesa.batch.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ayesa.batch.BatchLauncher.JOB_PARAMETERS;
import static com.ayesa.batch.BatchLauncher.TABLE_ENTITIES_IN_PROGRESS;
import static com.ayesa.batch.enums.CodeResponseOsinergminEnum.*;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.error.CommonErrorEnum.STATUS_PROCESSING;
import static com.ayesa.batch.enums.error.ErrorTypeEnum.FUNCIONAL;
import static com.ayesa.batch.enums.error.ErrorTypeEnum.TECNICO;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;


public class DataWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataWriter.class);
    private final NotificationService notificationService;
    private final PublicElectricityService publicElectricityService;
    private final JobNameEnum jobNameEnum;

    public DataWriter(JobNameEnum jobNameEnum) {
        this.jobNameEnum = jobNameEnum;
        publicElectricityService = new PublicElectricityServiceImpl(this.jobNameEnum);
        notificationService = new OutlookNotification();
    }

    public void writer(String fileName) {
        List<Map<String, Object>> entities = TABLE_ENTITIES_IN_PROGRESS.get(this.jobNameEnum);
        try {
            AbstractResponseDTO responseSubmit = publicElectricityService.submitInformationForProcessing(fileName);

            if (OSI_001.getCode().equals(responseSubmit.getCodigoMensaje())) {
                AbstractResponseDTO responseConfirm = publicElectricityService.confirmInformationSubmission();
                if (OSI_001.getCode().equals(responseConfirm.getCodigoMensaje())) {
                    LOGGER.info("{} CONFIRMADO CORRECTAMENTE", this.jobNameEnum.getTableName());
                    entities.forEach(entity -> {
                        updateStatusEntity(entity, StatusEnum.CONFIRMADO);
                    });
                    sendNotificationError(ErrorNotificationMapper.mapToUploadFileErrors(
                            entities,
                            this.jobNameEnum,
                            responseSubmit,
                            ErrorTypeEnum.NOT_ERROR
                    ));

                } else {
                    LOGGER.error("{} ERROR EN LA CONFIRMACION: {}", this.jobNameEnum.getTableName(), responseConfirm.getMensajeResultante());
                    entities.forEach(entity -> updateStatusEntity(entity, StatusEnum.ERROR));
                    ErrorOSIRepository.insert(
                            ErrorOSIMapper.mapToUploadFile(this.jobNameEnum, null, responseConfirm,null,FUNCIONAL)
                    );
                    sendNotificationError(ErrorNotificationMapper.mapToUploadFileErrors(
                            entities,
                            this.jobNameEnum,
                            responseSubmit,
                            ErrorTypeEnum.FUNCIONAL
                    ));

                }
            } else if ( OSI_302.getCode().equals(responseSubmit.getCodigoMensaje())) {
                LOGGER.error("{} ERROR EN LA REMISION: {}", this.jobNameEnum.getTableName(), responseSubmit.getMensajeResultante());
                responseSubmit.getListaErrores().forEach(error -> {
                    Map<String, Object> entity = entities.get(Integer.parseInt(error.getLinea()) - 1 );
                    updateStatusEntity(entity, StatusEnum.INVALIDO);
                    ErrorOSIRepository.insert(
                            ErrorOSIMapper.mapToUploadFile(this.jobNameEnum, entity, responseSubmit, null, FUNCIONAL)
                    );
                    entity.put(STATUS_PROCESSING.name(), StatusEnum.INVALIDO);
                });

                sendNotificationError(ErrorNotificationMapper.mapToUploadFileErrors(
                        entities,
                        this.jobNameEnum,
                        responseSubmit,
                        ErrorTypeEnum.FUNCIONAL
                ));

            }else if ( OSI_305.getCode().equals(responseSubmit.getCodigoMensaje()) ||
                    OSI_301.getCode().equals(responseSubmit.getCodigoMensaje())) {
                LOGGER.error("{} ERROR EN LA REMISION: {}", this.jobNameEnum.getTableName(), responseSubmit.getMensajeResultante());
                entities.forEach(entity -> updateStatusEntity(entity, StatusEnum.ERROR));
                ErrorOSIRepository.insert(
                        ErrorOSIMapper.mapToUploadFile(this.jobNameEnum, null, responseSubmit,null,FUNCIONAL)
                );

                sendNotificationError(ErrorNotificationMapper.mapToUploadFileErrors(
                        entities,
                        this.jobNameEnum,
                        responseSubmit,
                        ErrorTypeEnum.FUNCIONAL
                ));

            } else if (OSI_414.getCode().equals(responseSubmit.getCodigoMensaje())) {

                StringBuilder sb = new StringBuilder(responseSubmit.getMensajeResultante());
                sb.append(" - Periodo ").append(JOB_PARAMETERS.get(PERIODO_REMISION.name()));
                responseSubmit.setMensajeResultante(sb.toString());
                LOGGER.error("{} ERROR EN LA REMISION: {}", this.jobNameEnum.getTableName(), responseSubmit.getMensajeResultante());
                ErrorOSIRepository.insert(
                        ErrorOSIMapper.mapToUploadFile(this.jobNameEnum, null, responseSubmit,null,FUNCIONAL)
                );

                sendNotificationError(ErrorNotificationMapper.mapToUploadFileErrors(
                        entities,
                        this.jobNameEnum,
                        responseSubmit,
                        ErrorTypeEnum.FUNCIONAL
                ));
            }
        } catch (Exception e) {
            LOGGER.info("Error al enviar la remisión del archivo: {}", fileName, e);
            LOGGER.error(" ERROR EN LA REMISION: {}", this.jobNameEnum.getTableName(), e.getCause());
            ErrorOSIRepository.insert(
                    ErrorOSIMapper.mapToUploadFile(this.jobNameEnum, null, null,e,TECNICO)
            );

            sendNotificationError(ErrorNotificationMapper.mapToUploadFileErrors(
                    entities,
                    this.jobNameEnum,
                    null,
                    ErrorTypeEnum.TECNICO
            ));
        }
    }

    /**
     * Procesa una lista de registros de atención enviando cada uno al servicio de electricidad pública.
     * Dependiendo del código de respuesta, actualiza el estado del registro de atención a CONFIRMED o INVALID.
     * Si ocurre un error técnico durante el proceso, actualiza el estado a ERROR.
     *
     * @param attentionRegisters la lista de registros de atención a procesar
     */
    public void writer(List<Serializable> attentionRegisters) {
        List<AttentionRegisterRequestDTO> attentionRegisterRequestCasted = attentionRegisters.stream()
                        .map(x -> (AttentionRegisterRequestDTO) x)
                        .collect(Collectors.toList());

        attentionRegisterRequestCasted.forEach( attentionRegister -> {
            try {
                AbstractResponseDTO responseSubmit = publicElectricityService.submitAttentionRegister(attentionRegister);

                if (OSI_001.getCode().equals(responseSubmit.getCodigoMensaje())) {
                    updateStatusAttention(attentionRegister, StatusEnum.CONFIRMADO);
                    attentionRegister.setStatusProcessing(StatusEnum.CONFIRMADO);
                } else if (OSI_301.getCode().equals(responseSubmit.getCodigoMensaje()) ||
                        OSI_302.getCode().equals(responseSubmit.getCodigoMensaje()) ||
                        OSI_308.getCode().equals(responseSubmit.getCodigoMensaje())) {
                    LOGGER.error("{} ERROR FUNCIONAL EN REGISTRAR LA ATENCION: {}", this.jobNameEnum.getTableName(), attentionRegister.getCodigoAtencion());
                    updateStatusAttention(attentionRegister, StatusEnum.INVALIDO);
                    ErrorOSIRepository.insert(
                            ErrorOSIMapper.mapToAttention(this.jobNameEnum, attentionRegister, responseSubmit,null,FUNCIONAL)
                    );
                    attentionRegister.setStatusProcessing(StatusEnum.INVALIDO);
                }
            } catch (Exception e) {
                LOGGER.error("{} ERROR TECNICO EN REGISTRAR LA ATENCION: {}", this.jobNameEnum.getTableName(), attentionRegister.getCodigoAtencion(), e);
                updateStatusAttention(attentionRegister, StatusEnum.ERROR);
                ErrorOSIRepository.insert(
                        ErrorOSIMapper.mapToAttention(this.jobNameEnum, attentionRegister, null, e, TECNICO)
                );
                attentionRegister.setStatusProcessing(StatusEnum.ERROR);
            }
        });

        if(!attentionRegisterRequestCasted.isEmpty()){

            List<AttentionRegisterRequestDTO> attentionRegisterWithErrors = attentionRegisterRequestCasted.stream()
                    .filter(
                            x -> StatusEnum.ERROR.equals(x.getStatusProcessing())
                                    || StatusEnum.INVALIDO.equals(x.getStatusProcessing()))
                    .collect(Collectors.toList());

            sendNotificationError(ErrorNotificationMapper.mapToAttentionErrors(
                    attentionRegisterWithErrors,
                    this.jobNameEnum,
                    attentionRegisterRequestCasted
            ));
        }

    }


    private void updateStatusAttention(AttentionRegisterRequestDTO attention, StatusEnum statusEnum) {
        LOGGER.info("updateStatusAttention: {},{},{},{}",
                attention.getCodigoEmpresa(),
                attention.getCodigoAtencion(),
                attention.getNumeroSuministro(),
                statusEnum.name());


        TableRepository.update(
                this.jobNameEnum,
                statusEnum.name(),
                DateUtil.getCurrentDateTimeSql(DateUtil.FORMAT_DATETIME_1),
                attention.getCodigoEmpresa(),
                attention.getCodigoAtencion()
        );
    }

    private void updateStatusEntity(Map<String, Object> register, StatusEnum statusEnum) {

        LOGGER.info("updateStatusEntity: {}, {}, {}",
                register.get(COD_ATENCION.getFieldName()),
                register.get(COD_ACCION.getFieldName()),
                statusEnum.name());

        TableRepository.update(
                this.jobNameEnum,
                statusEnum.name(),
                DateUtil.getCurrentDateTimeSql(DateUtil.FORMAT_DATETIME_1),
                register.get(COD_ATENCION.getFieldName()),
                register.get(COD_ACCION.getFieldName())
        );
    }


    public void sendNotificationError( NotificationParameterDTO notificationParameterDTO) {
        notificationService.send(notificationParameterDTO);
    }
}


