package com.ayesa.batch;

import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.job.JobExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class BatchLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchLauncher.class);
    public static final String JOB_NAMES = "JOB_NAMES";
    public static final String CHUNK_SIZE = "CHUNK_SIZE";
    public static final String FILE_CREDENTIALS_BD = "FILE_CREDENTIALS_BD";
    public static Map<String, Object> JOB_PARAMETERS = new HashMap<>();

    public static void main(String[] args) {

        JobExecution jobExecution = new JobExecution();
        JOB_PARAMETERS = getJobParameters(args);
        jobExecution.start(getJobNames((String) JOB_PARAMETERS.get(JOB_NAMES)));

    }

    private static List<JobNameEnum> getJobNames(String jobNamesString) {
        if (Objects.isNull(jobNamesString)) {
            throw new IllegalArgumentException("JobNames incorrect format");
        }
        String[] splitNames = jobNamesString.split("-");
        Set<String> jobNames = new HashSet<>(Arrays.asList(splitNames));
        LOGGER.info("getJobNames: jobs = {}", jobNames);

        List<JobNameEnum> jobNameEnums = jobNames.stream()
                .map(x -> JobNameEnum.valueOf(JobNameEnum.class, x))
                .collect(Collectors.toList());
        LOGGER.info("getJobNames: jobs maps = {}", jobNameEnums);
        return jobNameEnums;
    }


    private static Map<String, Object> getJobParameters(String[] args) {
        LOGGER.info("getJobParameters: args = {}", Arrays.asList(args));
        if (args.length < 3) {
            throw new IllegalArgumentException("Faltan parámetros. Uso: <jobNames> <chunkSize> <fileCredentials> ");
        }

        String jobNames = args[0];
        String chunkSize = args[1];
        String fileCredentials = args[2];

        Map<String, Object> jobParameters = new HashMap<>();
        jobParameters.put(JOB_NAMES, jobNames);
        jobParameters.put(CHUNK_SIZE, Integer.parseInt(chunkSize));
        jobParameters.put(FILE_CREDENTIALS_BD, fileCredentials);
        LOGGER.info("getJobParameters: parameters = {}", jobParameters);
        return jobParameters;
    }

}
