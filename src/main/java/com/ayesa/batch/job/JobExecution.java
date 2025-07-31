package com.ayesa.batch.job;

import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.steps.DataProcessor;
import com.ayesa.batch.steps.DataReader;
import com.ayesa.batch.steps.DataWriter;
import com.ayesa.batch.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class JobExecution {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobExecution.class);

    public long start(List<JobNameEnum> jobNames) {


        AtomicBoolean isAdjustedPeriod = new AtomicBoolean(false);
        if (!jobNames.isEmpty()) {
            jobNames.stream().sorted().forEach(job -> {
                        try {

                            DataReader dataReader = new DataReader(job);
                            DataProcessor dataProcessor = new DataProcessor(job);
                            DataWriter dataWriter = new DataWriter(job);
                            Job jobExecute;
                            if (JobNameEnum.JOB01.name().equals(job.name())) {
                                jobExecute = new AttentionRegisterJobExecutable(job,dataReader,dataProcessor,dataWriter);
                            } else{
                                if(!isAdjustedPeriod.get()) {
                                    isAdjustedPeriod.set(true);
                                    DateUtil.adjustPeriodForBusinessDay();
                                }
                                jobExecute = new FileUploadJobExecutable(job,dataReader,dataProcessor,dataWriter);
                            }
                            jobExecute.run();
                        } catch (Exception exception) {
                            LOGGER.error("start: Error al ejecutar el job ::: ".concat(job.toString()), exception);
                        }
                    }
            );
        }
        return 0L;
    }

}
