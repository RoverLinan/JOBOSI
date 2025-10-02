package com.ayesa.batch;

import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.ParameterKitEnum;
import com.ayesa.batch.job.JobExecution;
import com.ayesa.batch.repository.TableRepository;
import com.ayesa.batch.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ayesa.batch.enums.JobParameterEnum.FILE_CREDENTIALS_BD;
import static com.ayesa.batch.enums.JobParameterEnum.JOB_NAMES;
import static com.ayesa.batch.enums.JobParameterEnum.OSI_PASS;
import static com.ayesa.batch.enums.JobParameterEnum.OSI_USER;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.URL_OSI;
import static com.ayesa.batch.util.DateUtil.FORMAT_DATETIME_3;
import static com.ayesa.batch.util.DateUtil.parseFromString;

public class BatchLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchLauncher.class);


    public static Map<String, Object> JOB_PARAMETERS = new HashMap<>();
    public static Map<JobNameEnum, List<Map<String,Object>>> TABLE_ENTITIES_IN_PROGRESS = new HashMap<>();


    public static void main(String[] args) {
        DataSourceConnection.getInstance();
        getJobParameters(args);
        new JobExecution().start(getJobNames((String) JOB_PARAMETERS.get(JOB_NAMES.name())));
        DataSourceConnection.closeConnection();
    }

    private static List<JobNameEnum> getJobNames(String jobNamesString) {
        if (Objects.isNull(jobNamesString)) {
            throw new IllegalArgumentException("JobNames incorrect format");
        }
        String[] splitNames = jobNamesString.split("-");
        Set<String> jobNames = new HashSet<>(Arrays.asList(splitNames));
        LOGGER.debug("getJobNames: jobs = {}", jobNames);

        List<JobNameEnum> jobNameEnums = jobNames.stream()
                .map(x -> JobNameEnum.valueOf(JobNameEnum.class, x))
                .collect(Collectors.toList());
        LOGGER.info("getJobNames: jobs maps = {}", jobNameEnums);
        return jobNameEnums;
    }


    private static void  getJobParameters(String[] args) {
        LOGGER.info("getJobParameters: args = {}", Arrays.asList(args));
        if (args.length < 1) {
            throw new IllegalArgumentException("Faltan parámetros. Uso: <jobNames> <periodoRemision> <fileCredentials>[Optional] ");
        }

        String jobNames = args[0];
        String periodoRemision = DateUtil.getCurrentDate(FORMAT_DATETIME_3);
        if(args.length == 2){
             periodoRemision = args[1];
        }


        JOB_PARAMETERS = new HashMap<>();
        JOB_PARAMETERS.put(JOB_NAMES.name(), jobNames);
        JOB_PARAMETERS.put(PERIODO_REMISION.name(), parseFromString(periodoRemision, FORMAT_DATETIME_3));

        if(args.length == 3){
            String fileCredentials = args[2];
            JOB_PARAMETERS.put(FILE_CREDENTIALS_BD.name(), fileCredentials);
        }

        loadParametersFromDb(ParameterKitEnum.BATCH_OSI_PARAMETERS);
        loadParametersFromDb(ParameterKitEnum.GENERIC_PARAMETERS);

        JOB_PARAMETERS.put(URL_OSI.name(), "https://prie.osinergmin.gob.pe");
        JOB_PARAMETERS.put(OSI_USER.name(),"lescobar");
        JOB_PARAMETERS.put(OSI_PASS.name(),"90AGxdGyz");
        LOGGER.info("getJobParameters: parameters = {}", JOB_PARAMETERS);
    }


    private static void loadParametersFromDb(ParameterKitEnum parameterKitEnum) {

        JOB_PARAMETERS.putAll( TableRepository.selectParameters(parameterKitEnum));
    }

}
