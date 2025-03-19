package com.ayesa.batch.job;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.steps.DataProcessor;
import com.ayesa.batch.steps.DataReader;
import com.ayesa.batch.steps.DataWriter;
import com.ayesa.batch.util.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.sql.*;
import java.util.Collections;

import static com.ayesa.batch.BatchLauncher.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class JobExecutableTest {

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


    private JobExecutable jobExecutable;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

        BatchLauncher.JOB_PARAMETERS.put(JOB_NAMES, Collections.emptyList());
        BatchLauncher.JOB_PARAMETERS.put(CHUNK_SIZE, 100);
        BatchLauncher.JOB_PARAMETERS.put(FILE_CREDENTIALS_BD, "C:\\Users\\Rover\\OneDrive\\Escritorio\\AYESA\\SDEV_BD_auth.properties");// update your local path
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


            jobExecutable = new JobExecutable(jobNameEnum, dataReader, dataProcessor, dataWriter);
        }



        jobExecutable.run();
        Mockito.verify(mockStatement, Mockito.times(1)).executeQuery(anyString());
        Mockito.verify(resultSetCount, Mockito.times(2)).next();
        Mockito.verify(resultSetCount, Mockito.times(1)).getInt(anyString());
        Mockito.verify(mockPreparedStatement, Mockito.times(4)).executeQuery();


    }



}