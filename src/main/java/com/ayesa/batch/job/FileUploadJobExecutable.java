package com.ayesa.batch.job;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import com.ayesa.batch.steps.DataProcessor;
import com.ayesa.batch.steps.DataReader;
import com.ayesa.batch.steps.DataWriter;
import com.ayesa.batch.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ayesa.batch.BatchLauncher.TABLE_ENTITIES_IN_PROGRESS;

public class FileUploadJobExecutable implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadJobExecutable.class);
    private final DataReader dataReader;
    private final DataProcessor dataProcessor;
    private final DataWriter dataWriter;
    private final JobNameEnum jobNameEnum;
    private final int CHUNK_SIZE;

    public FileUploadJobExecutable(JobNameEnum jobNameEnum, DataReader dataReader, DataProcessor dataProcessor, DataWriter dataWriter){
        LOGGER.debug("JobExecutable: jobNameEnum = {}", jobNameEnum);
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
        FileUtil.createFolder();
        TABLE_ENTITIES_IN_PROGRESS.put(jobNameEnum, new ArrayList<>());
        final String fileName = FileUtil.createFileName(jobNameEnum.getTableName(), FileUtil.FileTypeEnum.TXT);;
        for (int block = 0; block < DataReader.TOTAL_BLOCKS; block++) {
            int offset = block * CHUNK_SIZE;
            List<Map<String, Object>> dataRead =  dataReader.read(offset, CHUNK_SIZE);
            TABLE_ENTITIES_IN_PROGRESS.get(jobNameEnum).addAll(dataRead);
            dataProcessor.process(dataRead,fileName);
        }
        if (TABLE_ENTITIES_IN_PROGRESS.get(jobNameEnum).isEmpty()){
            dataWriter.writer(fileName);
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
