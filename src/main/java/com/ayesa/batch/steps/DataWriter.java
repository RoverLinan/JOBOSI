package com.ayesa.batch.steps;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.AttentionRegisterRequestDTO;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.StatusEnum;
import com.ayesa.batch.repository.TableRepository;
import com.ayesa.batch.service.PublicElectricityService;
import com.ayesa.batch.service.PublicElectricityServiceImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.ayesa.batch.BatchLauncher.TABLE_ENTITIES_IN_PROGRESS;
import static com.ayesa.batch.enums.CodeResponseOsinergminEnum.OSI_001;
import static com.ayesa.batch.enums.CodeResponseOsinergminEnum.OSI_301;
import static com.ayesa.batch.enums.CodeResponseOsinergminEnum.OSI_302;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ACCION;
import static com.ayesa.batch.mappers.fields.TableCommonFieldEnum.COD_ATENCION;


public class DataWriter {

    private final PublicElectricityService publicElectricityService;
    private final JobNameEnum jobNameEnum;

    public DataWriter(JobNameEnum jobNameEnum) {
        this.jobNameEnum = jobNameEnum;
        publicElectricityService = new PublicElectricityServiceImpl(this.jobNameEnum);
    }

    public void writer(String fileName) {
        try {
            AbstractResponseDTO responseSubmit = publicElectricityService.submitInformationForProcessing(fileName);
            List<Map<String, Object>> entities = TABLE_ENTITIES_IN_PROGRESS.get(this.jobNameEnum);

            if (OSI_001.getCode().equals(responseSubmit.getCodigoMensaje())) {
                AbstractResponseDTO responseConfirm = publicElectricityService.confirmInformationSubmission();
                if (OSI_001.getCode().equals(responseConfirm.getCodigoMensaje())) {
                    System.out.println("Envio y confirmacion exitosa");
                    entities.forEach(entity -> {
                        updateStatusEntity(entity, StatusEnum.CONFIRMED);
                    });
                } else {
                    System.out.println("Error funcional en la confirmacion");
                    System.out.println(responseSubmit.getCodigoMensaje());
                    System.out.println(responseSubmit.getMensajeResultante());
                }
            } else if ( OSI_302.getCode().equals(responseSubmit.getCodigoMensaje())) {

                responseSubmit.getListaErrores().forEach(error -> {
                    Map<String, Object> entity = entities.get(Integer.parseInt(error.getLinea()));
                    updateStatusEntity(entity, StatusEnum.INVALID);
                });

            }
        } catch (Exception e) {
            System.out.println("Error tecnico al enviar o confirmar: " + e.getMessage());
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

        attentionRegisters.forEach(attention -> {

            try {
                AbstractResponseDTO responseSubmit = publicElectricityService.submitAttentionRegister((AttentionRegisterRequestDTO) attention);

                if (OSI_001.getCode().equals(responseSubmit.getCodigoMensaje())) {
                    updateStatusAttention((AttentionRegisterRequestDTO) attention, StatusEnum.CONFIRMED);
                } else if (OSI_301.getCode().equals(responseSubmit.getCodigoMensaje()) ||
                        OSI_302.getCode().equals(responseSubmit.getCodigoMensaje())) {
                    updateStatusAttention((AttentionRegisterRequestDTO) attention, StatusEnum.INVALID);
                }
            } catch (Exception e) {
                System.out.println("Error tecnico al registrar la atencion: " + e.getMessage());
                updateStatusAttention((AttentionRegisterRequestDTO) attention, StatusEnum.ERROR);
            }
        });

    }


    private void updateStatusAttention(AttentionRegisterRequestDTO attention, StatusEnum statusEnum) {
        System.out.println("updateStatusAttention: " + attention.getCodigoEmpresa() + " " +
                attention.getCodigoAtencion() + " " +
                attention.getNumeroSuministro() + " " +
                statusEnum.name());
        TableRepository.update(
                this.jobNameEnum,
                statusEnum.name(),
                attention.getCodigoEmpresa(),
                attention.getCodigoAtencion(),
                attention.getNumeroSuministro()
        );
    }

    private void updateStatusEntity(Map<String, Object> register, StatusEnum statusEnum) {
        System.out.println("updateStatusEntity: " + register.get(COD_ATENCION.getFieldName()) + " " +
                register.get(COD_ACCION.getFieldName()) + " " +
                statusEnum.name());
        TableRepository.update(
                this.jobNameEnum,
                statusEnum.name(),
                (String) register.get(COD_ATENCION.getFieldName()),
                (String) register.get(COD_ACCION.getFieldName())
        );
    }
}


