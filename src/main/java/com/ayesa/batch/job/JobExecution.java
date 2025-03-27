package com.ayesa.batch.job;

import com.ayesa.batch.business.exception.LogicalException;
import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.steps.DataProcessor;
import com.ayesa.batch.steps.DataReader;
import com.ayesa.batch.steps.DataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JobExecution {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobExecution.class);

    public long start(List<JobNameEnum> jobNames) {
        if (!jobNames.isEmpty()) {
            jobNames.stream().sorted().forEach(x -> {
                        try {
                            DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
                            DataReader dataReader = new DataReader(x,dataSourceConnection);
                            DataProcessor dataProcessor = new DataProcessor(x);
                            DataWriter dataWriter = new DataWriter(x);
                            Job jobExecute = new JobExecutable(x,dataReader,dataProcessor,dataWriter);
                            jobExecute.run();
                        } catch (LogicalException exception) {
                            LOGGER.error("start: Error al ejecutar el job ::: ".concat(x.toString()), exception.getException());
                        }
                    }
            );
        }
        return 0L;
    }

}
