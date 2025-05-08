package com.ayesa.batch.mappers.error;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.ayesa.batch.business.dto.notification.ParameterTemplateDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.enums.JobNameEnum;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.ayesa.batch.enums.JobParameterEnum.*;

public class ErrorNotificationMapper {
    public static MailParameterDTO mapToAttentionErrors(List<Serializable> attentionRegisterWithErrors, JobNameEnum jobNameEnum, List<AttentionRegisterRequestDTO> attentionRegisterRequestCasted) {
        ParameterTemplateDTO parameters = new ParameterTemplateDTO();

        parameters.setC001((String) BatchLauncher.JOB_PARAMETERS.get(EMAIL_NOT.name()));
        parameters.setC002(jobNameEnum.getTableName());
        parameters.setC003((String) BatchLauncher.JOB_PARAMETERS.get(PERIODO_REMISION.name()));
        parameters.setC004(String.valueOf(attentionRegisterWithErrors.size()));

        List<String> errorAttentionDetails = attentionRegisterWithErrors.stream()
                .map(attentionRegister -> {
                    AttentionRegisterRequestDTO attentionRegisterRequest = (AttentionRegisterRequestDTO) attentionRegister;
                    return attentionRegisterRequest.getCodigoAtencion();
                })
                .collect(Collectors.toList());
        parameters.setC005(String.join(", ", errorAttentionDetails));
        parameters.setC006(String.valueOf(attentionRegisterRequestCasted.size()));

        MailParameterDTO mailParameterDTO = new MailParameterDTO();
        mailParameterDTO.setService_id((String)BatchLauncher.JOB_PARAMETERS.get(SRV_ID_NOT.name()));
        mailParameterDTO.setTemplate_id((String)BatchLauncher.JOB_PARAMETERS.get(TEM_ID_NOT.name()));
        mailParameterDTO.setUser_id((String)BatchLauncher.JOB_PARAMETERS.get(USR_ID_NOT.name()));
        mailParameterDTO.setAccessToken((String)BatchLauncher.JOB_PARAMETERS.get(AC_TK_NOT.name()));
        mailParameterDTO.setTemplate_params(parameters);

        return mailParameterDTO;
    }
}
