package com.ayesa.batch.steps;

import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.service.PublicElectricityService;
import com.ayesa.batch.service.PublicElectricityServiceImpl;


public class DataWriter {

    private PublicElectricityService publicElectricityService;
    private final JobNameEnum jobName;

    public DataWriter(JobNameEnum jobNameEnum){
        this.jobName = jobNameEnum;
        publicElectricityService = new PublicElectricityServiceImpl();
    }
    public void writer(String fileName) {
        try {
            publicElectricityService.submitInformationForProcessing(fileName, jobName);
            publicElectricityService.confirmInformationSubmission();
        }catch (Exception e){
            publicElectricityService.revertInformationConfirmation();
        }
    }
}


