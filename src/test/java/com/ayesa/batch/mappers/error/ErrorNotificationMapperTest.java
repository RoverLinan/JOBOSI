package com.ayesa.batch.mappers.error;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.ayesa.batch.enums.JobNameEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.ayesa.batch.enums.JobParameterEnum.HOST_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.JOB_NAMES;
import static com.ayesa.batch.enums.JobParameterEnum.KIT_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.PORT_NOT;
import static org.junit.Assert.*;

public class ErrorNotificationMapperTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void mapToAttentionErrors() {


        mockJobParameters();

        MailParameterDTO mailParameterDTO = ErrorNotificationMapper.mapToAttentionErrors(
                Collections.emptyList(),
                JobNameEnum.JOB01,
                Collections.emptyList());


        assertNotNull(mailParameterDTO);
    }


    private void mockJobParameters(){
        Map<String, Object> JOB_PARAMETERS = new HashMap<>();
        JOB_PARAMETERS.put(JOB_NAMES.name(), "JOB01");
        JOB_PARAMETERS.put(PERIODO_REMISION.name(), "202503");
        JOB_PARAMETERS.put(HOST_NOT.name(), "smtp.example.com");
        JOB_PARAMETERS.put(PORT_NOT.name(), 587);
        JOB_PARAMETERS.put(KIT_NOT.name(), "KITERR-01");


        BatchLauncher.JOB_PARAMETERS = JOB_PARAMETERS;
    }
}