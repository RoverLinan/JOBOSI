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

import static com.ayesa.batch.enums.JobParameterEnum.CHUNK_SIZE;
import static com.ayesa.batch.enums.JobParameterEnum.CODIGO;
import static com.ayesa.batch.enums.JobParameterEnum.FILE_CREDENTIALS_BD;
import static com.ayesa.batch.enums.JobParameterEnum.JOB_NAMES;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.enums.JobParameterEnum.VALOR_ALF;
import static com.ayesa.batch.enums.JobParameterEnum.VALOR_NUM;

public class BatchLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchLauncher.class);


    public static Map<String, Object> JOB_PARAMETERS = new HashMap<>();
    public static Map<JobNameEnum, List<Map<String,Object>>> TABLE_ENTITIES_IN_PROGRESS = new HashMap<>();


    public static void main(String[] args) {
        getJobParameters(args);
        new JobExecution().start(getJobNames((String) JOB_PARAMETERS.get(JOB_NAMES.name())));

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
        if (args.length < 2) {
            throw new IllegalArgumentException("Faltan parámetros. Uso: <jobNames> <periodoRemision> <fileCredentials>[Optional] ");
        }

        String jobNames = args[0];
        String periodoRemision = args[1];

        JOB_PARAMETERS = new HashMap<>();
        JOB_PARAMETERS.put(JOB_NAMES.name(), jobNames);
        JOB_PARAMETERS.put(PERIODO_REMISION.name(), periodoRemision);

        if(args.length == 3){
            String fileCredentials = args[2];
            JOB_PARAMETERS.put(FILE_CREDENTIALS_BD.name(), fileCredentials);
        }

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
                parameter.put(VALOR_NUM.name(), resultSet.getInt(VALOR_NUM.name()));
                parameter.put(VALOR_ALF.name(), resultSet.getString(VALOR_ALF.name()));
                parameter.put(CODIGO.name(), resultSet.getString(CODIGO.name()));
                parameters.add(parameter);
            }

            parameters.forEach(p -> {
                String codigo = (String)p.get(CODIGO.name());
                if(Objects.nonNull(codigo)){
                    if(CHUNK_SIZE.name().equals(codigo)){
                        JOB_PARAMETERS.put(CHUNK_SIZE.name(), p.get(VALOR_NUM.name()));
                    }else{
                        JOB_PARAMETERS.put(codigo, p.get(VALOR_ALF.name()));
                    }

                }
            });

        } catch (Exception e) {
            LOGGER.error("Error loading parameters from DB", e);
        }


    }




}
