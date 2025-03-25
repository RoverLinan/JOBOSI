package com.ayesa.batch;

import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.ParameterKitEnum;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.job.JobExecution;
import com.ayesa.batch.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class BatchLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchLauncher.class);
    public static final String JOB_NAMES = "JOB_NAMES";
    public static final String CHUNK_SIZE = "CHUNK_SIZE";
    public static final String FILE_CREDENTIALS_BD = "FILE_CREDENTIALS_BD";
    public static final String PERIODO_REMISION = "PERIODO_REMISION";
    public static final String CODIGO = "CODIGO";
    public static final String VALOR_ALF = "VALOR_ALF";
    public static final String VALOR_NUM = "VALOR_NUM";


    public static Map<String, Object> JOB_PARAMETERS = new HashMap<>();


    public static void main(String[] args) {

        JobExecution jobExecution = new JobExecution();
        getJobParameters(args);
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


    private static void  getJobParameters(String[] args) {
        LOGGER.info("getJobParameters: args = {}", Arrays.asList(args));
        if (args.length < 3) {
            throw new IllegalArgumentException("Faltan parámetros. Uso: <jobNames> <periodoRemision> <fileCredentials> ");
        }

        String jobNames = args[0];
        String periodoRemision = args[1];
        String fileCredentials = args[2];

        JOB_PARAMETERS = new HashMap<>();
        JOB_PARAMETERS.put(JOB_NAMES, jobNames);
        JOB_PARAMETERS.put(PERIODO_REMISION, periodoRemision);
        JOB_PARAMETERS.put(FILE_CREDENTIALS_BD, fileCredentials);


        loadParametersFromDb(ParameterKitEnum.BATCH_OSI_PARAMETERS);
        loadParametersFromDb(ParameterKitEnum.GENERIC_PARAMETERS);
        LOGGER.info("getJobParameters: parameters = {}", JOB_PARAMETERS);
    }


    private static void loadParametersFromDb(ParameterKitEnum parameterKitEnum) {

        DataSourceConnection dataSource = DataSourceConnection.getInstance();
        String queryRead = FileUtil.getPropertiesFromResources().getProperty(QueryNameEnum.SQL_PARAMETERS_SELECT_ALL.getPropertyName());
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryRead )) {


            preparedStatement.setString(1, parameterKitEnum.getParameterFamily());
            preparedStatement.setString(2, parameterKitEnum.getParameterKit());

            List<Map<String, Object>> parameters = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> parameter = new HashMap<>();
                parameter.put(VALOR_NUM, resultSet.getInt(VALOR_NUM));
                parameter.put(VALOR_ALF, resultSet.getString(VALOR_ALF));
                parameter.put(CODIGO, resultSet.getString(CODIGO));
                parameters.add(parameter);
            }

            parameters.forEach(p -> {
                String codigo = (String)p.get(CODIGO);
                if(Objects.nonNull(codigo)){
                    if(CHUNK_SIZE.equals(codigo)){
                        JOB_PARAMETERS.put(CHUNK_SIZE, p.get(VALOR_NUM));
                    }else{
                        JOB_PARAMETERS.put(codigo, p.get(VALOR_ALF));
                    }

                }
            });

        } catch (Exception e) {
            LOGGER.error("Error loading parameters from DB", e);
        }


    }




}
