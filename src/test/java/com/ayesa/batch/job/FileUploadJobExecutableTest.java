package com.ayesa.batch.job;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.steps.DataProcessor;
import com.ayesa.batch.steps.DataReader;
import com.ayesa.batch.steps.DataWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.sql.*;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FileUploadJobExecutableTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet resultSetCount;
    @Mock
    private ResultSet resultSetSelect;


    private FileUploadJobExecutable fileUploadJobExecutable;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

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

        BatchLauncher.JOB_PARAMETERS.put(FILE_CREDENTIALS_BD.name(), "C:\\Users\\Rover\\OneDrive\\Escritorio\\AYESA\\SDEV_BD_auth.properties");// update your local path
    }

    @After
    public void tearDown() throws Exception {
       Mockito.reset(  resultSetSelect);
    }

    @Test
    public void runJob01() throws Exception {

        final JobNameEnum jobNameEnum = JobNameEnum.JOB01;


        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection);

            mockedDriverManager.when(() -> mockConnection.createStatement())
                    .thenReturn(mockStatement);


            mockedDriverManager.when(() -> mockConnection.prepareStatement(anyString()))
                    .thenReturn(mockPreparedStatement);


            mockedDriverManager.when(() -> mockStatement.executeQuery(anyString()))
                    .thenReturn(resultSetCount);

            when(mockPreparedStatement.executeQuery()).thenReturn(resultSetSelect);
            when(resultSetCount.next()).thenReturn(true).thenReturn(false);
            when(resultSetCount.getInt(anyString())).thenReturn(400);
            when(resultSetSelect.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            doNothing().when(mockConnection).close();


            DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();


            DataReader dataReader = new DataReader(jobNameEnum, dataSourceConnection);


            DataProcessor dataProcessor = new DataProcessor(jobNameEnum);
            DataWriter dataWriter = new DataWriter(jobNameEnum);


            fileUploadJobExecutable = new FileUploadJobExecutable(jobNameEnum, dataReader, dataProcessor, dataWriter);
        }



        fileUploadJobExecutable.run();

        Mockito.verify(resultSetCount, Mockito.times(2)).next();
        Mockito.verify(resultSetCount, Mockito.times(1)).getInt(anyString());
        Mockito.verify(mockPreparedStatement, Mockito.times(4)).executeQuery();


    }



}