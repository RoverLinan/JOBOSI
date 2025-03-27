package com.ayesa.batch.steps;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.enums.CodeResponseOsinergminEnum;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.service.PublicElectricityService;
import com.ayesa.batch.service.PublicElectricityServiceImpl;


public class DataWriter {

    private final PublicElectricityService publicElectricityService;

    public DataWriter(JobNameEnum jobNameEnum){
        publicElectricityService = new PublicElectricityServiceImpl(jobNameEnum);
    }
    public void writer(String fileName) {
        try {
            AbstractResponseDTO responseSubmit = publicElectricityService.submitInformationForProcessing(fileName);

            if(CodeResponseOsinergminEnum.OSI_001.getCode().equals(responseSubmit.getCodigoMensaje())) {
                AbstractResponseDTO responseConfirm = publicElectricityService.confirmInformationSubmission();
                if(CodeResponseOsinergminEnum.OSI_001.getCode().equals(responseConfirm.getCodigoMensaje())) {
                    System.out.println("Envio y confirmacion exitosa");
                }else {
                    System.out.println("Error funcional en la confirmacion");
                    System.out.println(responseSubmit.getCodigoMensaje());
                    System.out.println(responseSubmit.getMensajeResultante());
                }
            }else {
                System.out.println("Error funcional en el envio");
                System.out.println(responseSubmit.getCodigoMensaje());
                System.out.println(responseSubmit.getMensajeResultante());
            }
        }catch (Exception e){
            System.out.println("Error tecnico al enviar o confirmar: " + e.getMessage());
        }
    }
}


