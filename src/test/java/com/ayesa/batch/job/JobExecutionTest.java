package com.ayesa.batch.job;


import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.enums.JobNameEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.ayesa.batch.BatchLauncher.*;
import static com.ayesa.batch.enums.JobParameterEnum.CHAR_DELIM;
import static com.ayesa.batch.enums.JobParameterEnum.CHUNK_SIZE;
import static com.ayesa.batch.enums.JobParameterEnum.CODEMP;
import static com.ayesa.batch.enums.JobParameterEnum.FILE_CREDENTIALS_BD;
import static com.ayesa.batch.enums.JobParameterEnum.JOB_NAMES;
import static com.ayesa.batch.enums.JobParameterEnum.OSI_PASS;
import static com.ayesa.batch.enums.JobParameterEnum.OSI_USER;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.URL_CONFI;
import static com.ayesa.batch.enums.JobParameterEnum.URL_OSI;
import static com.ayesa.batch.enums.JobParameterEnum.URL_REMI;
import static com.ayesa.batch.enums.JobParameterEnum.URL_REVER;

public class JobExecutionTest {


    private JobExecution jobExecution;




    @Before
    public void setUp() throws Exception {
        jobExecution = new JobExecution();
        BatchLauncher.JOB_PARAMETERS.put(JOB_NAMES.name(), Collections.emptyList());
        BatchLauncher.JOB_PARAMETERS.put(CHUNK_SIZE.name(), 100);
        BatchLauncher.JOB_PARAMETERS.put(URL_OSI.name(), "http://192.168.18.194:9600");
        BatchLauncher.JOB_PARAMETERS.put(URL_REMI.name(), "/tisec-ws/remote/rest/remisionDatos");
        BatchLauncher.JOB_PARAMETERS.put(URL_CONFI.name(), "/tisec-ws/remote/rest/remisionDatos/confirmar");
        BatchLauncher.JOB_PARAMETERS.put(URL_REVER.name(), "/tisec-ws/remote/rest/remisionDatos/revertir");
        BatchLauncher.JOB_PARAMETERS.put(CHAR_DELIM.name(), "|");
        BatchLauncher.JOB_PARAMETERS.put(FILE_CREDENTIALS_BD.name(), "C:\\Users\\Rover\\OneDrive\\Escritorio\\AYESA\\SDEV_BD_auth.properties");// update your local path
        JOB_PARAMETERS.put(PERIODO_REMISION.name(), "202101");
        JOB_PARAMETERS.put(OSI_USER.name(), "user");
        JOB_PARAMETERS.put(OSI_PASS.name(), "pass");
        JOB_PARAMETERS.put(CODEMP.name(), "0001");
    }

    @Test
    public void start() {
        jobExecution.start(Collections.singletonList( JobNameEnum.JOB03));
    }
}