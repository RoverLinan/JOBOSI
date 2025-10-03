package com.ayesa.batch.job;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import com.ayesa.batch.mappers.AbstractEntityMapper;
import com.ayesa.batch.mappers.EntityMapperCreator;
import com.ayesa.batch.steps.DataProcessor;
import com.ayesa.batch.steps.DataReader;
import com.ayesa.batch.steps.DataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttentionRegisterJobExecutable implements Job{

    private static final Logger LOGGER = LoggerFactory.getLogger(AttentionRegisterJobExecutable.class);
    private final DataReader dataReader;
    private final DataProcessor dataProcessor;
    private final DataWriter dataWriter;
    private final JobNameEnum jobNameEnum;
    private final int CHUNK_SIZE;

    public AttentionRegisterJobExecutable(JobNameEnum jobNameEnum, DataReader dataReader, DataProcessor dataProcessor, DataWriter dataWriter){
        LOGGER.info("JobExecutable: jobNameEnum = {}", jobNameEnum);
        this.jobNameEnum = jobNameEnum;
        this.CHUNK_SIZE = (int) BatchLauncher.JOB_PARAMETERS.get(JobParameterEnum.CHUNK_SIZE.name());
        this.dataReader = dataReader;
        this.dataReader.countElements();
        this.dataProcessor = dataProcessor;
        this.dataWriter = dataWriter;

        LOGGER.info("JobExecutable: initial totalElements = {}, totalBlocks = {}", DataReader.TOTAL_ELEMENTS, DataReader.TOTAL_BLOCKS );
    }

    @Override
    public void run(){
        LOGGER.info("JobExecutable: run init");
        List<Serializable>  attentionRegisters = new ArrayList<>();
        AbstractEntityMapper mapper = EntityMapperCreator.create(jobNameEnum);
        for (int block = 0; block < 1; block++) {

            List<Map<String, Object>> dataRead =  dataReader.read(0, 4000);
            attentionRegisters = dataProcessor.process(dataRead, mapper);
        }
        if (!attentionRegisters.isEmpty()){
            dataWriter.writer(attentionRegisters);
        }
        LOGGER.info("JobExecutable: run end");
    }

    public DataReader getDataReader() {
        return dataReader;
    }

    public DataProcessor getDataProcessor() {
        return dataProcessor;
    }

    public DataWriter getDataWriter() {
        return dataWriter;
    }
}
