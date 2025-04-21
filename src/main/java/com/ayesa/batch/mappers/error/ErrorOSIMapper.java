package com.ayesa.batch.mappers.error;

import com.ayesa.batch.business.bo.ErrorOSIBO;
import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.enums.JobNameEnum;

import java.util.Map;
import java.util.Objects;

import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;

public class ErrorOSIMapper {


    public static ErrorOSIBO mapToAttention(JobNameEnum jobNameEnum, AttentionRegisterRequestDTO attentionRegisterRequestDTO, AbstractResponseDTO abstractResponseDTO, Exception exception, String errorType){
        ErrorOSIBO errorOSIBO = new ErrorOSIBO(jobNameEnum.getTableName(), attentionRegisterRequestDTO.getCodigoAtencion(),errorType);
        if(Objects.nonNull(abstractResponseDTO)){
            errorOSIBO.setComentariosAdicionales(abstractResponseDTO.toString());
        } else if (Objects.nonNull(exception)) {
            errorOSIBO.setComentariosAdicionales(exception.toString());
        }
        return errorOSIBO;
    }

    public static ErrorOSIBO mapToUploadFile(JobNameEnum jobNameEnum, Map<String, Object> entity, AbstractResponseDTO abstractResponseDTO, Exception exception, String errorType) {
        ErrorOSIBO errorOSIBO;
        if(Objects.nonNull(entity)){
            errorOSIBO = new ErrorOSIBO(jobNameEnum.getTableName(), (String) entity.get(COD_ATENCION.getFieldName()),errorType);
            errorOSIBO.setCodAccion((String) entity.get(COD_ACCION.getFieldName()));
        }else{
            errorOSIBO = new ErrorOSIBO(jobNameEnum.getTableName(), "NA",errorType);
            errorOSIBO.setCodAccion("NA");
        }

        if(Objects.nonNull(abstractResponseDTO)){
            errorOSIBO.setComentariosAdicionales(abstractResponseDTO.toString());
        }
        return errorOSIBO;
    }
}
