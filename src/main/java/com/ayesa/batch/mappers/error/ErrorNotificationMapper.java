package com.ayesa.batch.mappers.error;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.StatusEnum;
import com.ayesa.batch.enums.error.ErrorTypeEnum;
import com.ayesa.batch.enums.notification.TemplateNameEnum;
import com.ayesa.batch.mappers.fields.MailTemplateFieldEnum;
import com.ayesa.batch.util.DateUtil;
import com.ayesa.batch.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ayesa.batch.enums.JobParameterEnum.ASUNTO_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.COPIA_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.EMAIL1_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.EMAIL2_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.HOST_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.KIT_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.PORT_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.TYPE_NOT;
import static com.ayesa.batch.enums.error.CommonErrorEnum.STATUS_PROCESSING;
import static com.ayesa.batch.util.FileUtil.PATH_RESOURCE_TEMPLATE_MAIL;

public class ErrorNotificationMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorNotificationMapper.class);
    private static final int DEFAULT_OPTION_MAIL = 0;
    public static MailParameterDTO mapToAttentionErrors(List<AttentionRegisterRequestDTO> attentionRegisterWithErrors, JobNameEnum jobNameEnum, List<AttentionRegisterRequestDTO> attentionRegisterRequestCasted) {


        ErrorTypeEnum errorTypeEnum = attentionRegisterWithErrors.isEmpty() ? ErrorTypeEnum.NOT_ERROR: ErrorTypeEnum.FUNCIONAL;
        String errorDescription = "Demasiados errores para mostrar";
        if(attentionRegisterWithErrors.size() < 15){
            errorDescription = attentionRegisterWithErrors.stream()
                    .map(AttentionRegisterRequestDTO::getCodigoAtencion)
                    .collect(Collectors.joining(", "));
        }


        Map<MailTemplateFieldEnum, Object> parameters = initializeTemplateParameter(jobNameEnum,errorTypeEnum, "N/A");
        parameters.put(MailTemplateFieldEnum.COUNT_ERRORS_FIELD, attentionRegisterWithErrors.size());
        parameters.put(MailTemplateFieldEnum.COUNT_PROCESSED_FIELD, attentionRegisterRequestCasted.size());
        parameters.put(MailTemplateFieldEnum.ERROR_DESCRIPTION_FIELD, errorDescription );

        MailParameterDTO mailParameterDTO = initializeMailparameterDTO();
        mailParameterDTO.setSubject( replaceParameterInSubject ((String) BatchLauncher.JOB_PARAMETERS.get(ASUNTO_NOT.name()), parameters));
        mailParameterDTO.setMessage( replaceParameterInTemplate((String) BatchLauncher.JOB_PARAMETERS.get(KIT_NOT.name()), parameters));

        return mailParameterDTO;
    }


    private static String replaceParameterInTemplate(String kitName, Map<MailTemplateFieldEnum, Object> parameters) {

        TemplateNameEnum templateNameEnum = TemplateNameEnum.fromKitName(kitName);

        String templateMail = FileUtil.getPropertiesFromResources(PATH_RESOURCE_TEMPLATE_MAIL).getProperty(templateNameEnum.getPropertyName());
        LOGGER.debug("replaceParameterInTemplate: templateNameEnum = {}, templateMail = {}", templateNameEnum, templateMail);
        return replaceParameter(templateMail, parameters);
    }

    private static String replaceParameterInSubject(String subject, Map<MailTemplateFieldEnum, Object> parameters) {
        return replaceParameter(subject, parameters);
    }

    private static String replaceParameter(String value, Map<MailTemplateFieldEnum, Object> parameters) {
        if(Objects.nonNull(value) && Objects.nonNull(parameters) ) {
            for (Map.Entry<MailTemplateFieldEnum, Object> entry : parameters.entrySet()) {
                String placeholder = "{{" + entry.getKey().getFieldName() + "}}";
                value = value.replace(placeholder, entry.getValue() != null ? entry.getValue().toString() : "");
            }
        }
        return value;
    }

    public static MailParameterDTO mapToUploadFileErrors(List<Map<String, Object>> entities, JobNameEnum jobNameEnum, AbstractResponseDTO responseSubmit, ErrorTypeEnum errorTypeEnum) {

        String errorCode = Objects.nonNull(responseSubmit) ? responseSubmit.getCodigoMensaje() : "N/A";
        String errorDescription = "Demasiados errores para mostrar";
        if(Objects.nonNull(responseSubmit) && Objects.nonNull(responseSubmit.getListaErrores()) && responseSubmit.getListaErrores().size() < 15){
            errorDescription = responseSubmit.getListaErrores().toString();
        }


        Map<MailTemplateFieldEnum, Object> parameters = initializeTemplateParameter(jobNameEnum, errorTypeEnum, errorCode);
        parameters.put(MailTemplateFieldEnum.COUNT_ERRORS_FIELD, entities.stream().filter( value -> StatusEnum.INVALIDO.equals(value.get(STATUS_PROCESSING.name()))).count() );
        parameters.put(MailTemplateFieldEnum.COUNT_PROCESSED_FIELD, entities.size());
        parameters.put(MailTemplateFieldEnum.ERROR_DESCRIPTION_FIELD, errorDescription);



       MailParameterDTO mailParameterDTO = initializeMailparameterDTO();
        mailParameterDTO.setSubject(replaceParameterInSubject((String) BatchLauncher.JOB_PARAMETERS.get(ASUNTO_NOT.name()), parameters));
        mailParameterDTO.setMessage(replaceParameterInTemplate((String) BatchLauncher.JOB_PARAMETERS.get(KIT_NOT.name()), parameters));


      return mailParameterDTO;
    }

    private static MailParameterDTO initializeMailparameterDTO() {
        MailParameterDTO mailParameterDTO = new MailParameterDTO();

        mailParameterDTO.setHost((String) BatchLauncher.JOB_PARAMETERS.get(HOST_NOT.name()));
        mailParameterDTO.setPort((int) BatchLauncher.JOB_PARAMETERS.get(PORT_NOT.name()));
        mailParameterDTO.setMailFrom((String) BatchLauncher.JOB_PARAMETERS.get(EMAIL1_NOT.name()));
        mailParameterDTO.setMailTo((String) BatchLauncher.JOB_PARAMETERS.get(EMAIL2_NOT.name()));
        mailParameterDTO.setCc((String) BatchLauncher.JOB_PARAMETERS.get(COPIA_NOT.name()));
        mailParameterDTO.setType((String) BatchLauncher.JOB_PARAMETERS.get(TYPE_NOT.name()));
        mailParameterDTO.setOption(DEFAULT_OPTION_MAIL);
        return mailParameterDTO;
    }

    private static Map<MailTemplateFieldEnum, Object> initializeTemplateParameter(JobNameEnum jobNameEnum, ErrorTypeEnum errorTypeEnum, String errorCode) {

        Map<MailTemplateFieldEnum, Object> parameters = new HashMap<>();

        parameters.put(MailTemplateFieldEnum.TABLE_NAME_FIELD, jobNameEnum.getTableName().concat("( ").concat(jobNameEnum.getTableNameBD()).concat(" )"));
        parameters.put(MailTemplateFieldEnum.PERIOD_FIELD, DateUtil.parseFromLocalDate( BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name())) );
        parameters.put(MailTemplateFieldEnum.DATE_FIELD, DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME_4));
        parameters.put(MailTemplateFieldEnum.ERROR_TYPE_FIELD, errorTypeEnum.name());
        parameters.put(MailTemplateFieldEnum.ERROR_CODE_FIELD, errorCode);

        return parameters;
    }
}
