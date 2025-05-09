package com.ayesa.batch.mappers.error;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.notification.DetailErrorMailDTO;
import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.ayesa.batch.business.dto.notification.ParameterTemplateDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.util.DateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.ayesa.batch.enums.JobParameterEnum.*;

public class ErrorNotificationMapper {
    public static MailParameterDTO mapToAttentionErrors(List<AttentionRegisterRequestDTO> attentionRegisterWithErrors, JobNameEnum jobNameEnum, List<AttentionRegisterRequestDTO> attentionRegisterRequestCasted) {
        ParameterTemplateDTO parameters = new ParameterTemplateDTO();

        parameters.setMailTo((String) BatchLauncher.JOB_PARAMETERS.get(EMAIL_NOT.name()));
        parameters.setTableName(jobNameEnum.getTableName());
        parameters.setPeriod((String) BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name()));
        parameters.setCountErrors(String.valueOf(attentionRegisterWithErrors.size()));

        List<DetailErrorMailDTO> errorAttentionDetails = attentionRegisterWithErrors.stream()
                .map(attentionRegister -> {
                    DetailErrorMailDTO detailErrorMailDTO = new DetailErrorMailDTO();
                    detailErrorMailDTO.setAttentionId(attentionRegister.getCodigoAtencion());
                    detailErrorMailDTO.setActionId("N/A");
                    detailErrorMailDTO.setDescription(attentionRegister.getStatusProcessing().name());
                    return detailErrorMailDTO;
                })
                .collect(Collectors.toList());
        parameters.setDetails(errorAttentionDetails);
        parameters.setCountProcessed(String.valueOf(attentionRegisterRequestCasted.size()));
        parameters.setDate(DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME_4));
        parameters.setUserId((String) BatchLauncher.JOB_PARAMETERS.get(OSI_USER.name()));


        MailParameterDTO mailParameterDTO = new MailParameterDTO();
        mailParameterDTO.setService_id((String)BatchLauncher.JOB_PARAMETERS.get(SRV_ID_NOT.name()));
        mailParameterDTO.setTemplate_id((String)BatchLauncher.JOB_PARAMETERS.get(TEM_ID_NOT.name()));
        mailParameterDTO.setUser_id((String)BatchLauncher.JOB_PARAMETERS.get(USR_ID_NOT.name()));
        mailParameterDTO.setAccessToken((String)BatchLauncher.JOB_PARAMETERS.get(AC_TK_NOT.name()));
        mailParameterDTO.setTemplate_params(parameters);


        return mailParameterDTO;
    }
}
