package com.ayesa.batch.job;


import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.enums.JobNameEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.ayesa.batch.BatchLauncher.*;

public class JobExecutionTest {


    private JobExecution jobExecution;




    @Before
    public void setUp() throws Exception {
        jobExecution = new JobExecution();
        BatchLauncher.JOB_PARAMETERS.put(JOB_NAMES, Collections.emptyList());
        BatchLauncher.JOB_PARAMETERS.put(CHUNK_SIZE, 100);
        BatchLauncher.JOB_PARAMETERS.put(FILE_CREDENTIALS_BD,"C:\\Users\\Rover\\OneDrive\\Escritorio\\AYESA\\SDEV_BD_auth.properties");// update your local path
    }

    @Test
    public void start() {
        jobExecution.start(Collections.singletonList( JobNameEnum.JOB03));
    }
}