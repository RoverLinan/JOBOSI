package com.ayesa.batch.steps;

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
            publicElectricityService.submitInformationForProcessing(fileName);
            publicElectricityService.confirmInformationSubmission();
        }catch (Exception e){
            publicElectricityService.revertInformationConfirmation();
        }
    }
}


