package com.ayesa.batch.repository;

import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.ParameterKitEnum;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ayesa.batch.enums.JobParameterEnum.CHUNK_SIZE;
import static com.ayesa.batch.enums.JobParameterEnum.CODIGO;
import static com.ayesa.batch.enums.JobParameterEnum.PORT_NOT;
import static com.ayesa.batch.enums.JobParameterEnum.VALOR_ALF;
import static com.ayesa.batch.enums.JobParameterEnum.VALOR_NUM;
import static com.ayesa.batch.util.FileUtil.PATH_RESOURCES_SQL_QUERIES;

public class TableRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableRepository.class);
    private TableRepository(){}
    public static int update(JobNameEnum jobName, Object... params) {
        QueryNameEnum queryNameEnum = QueryNameEnum.inverse(jobName, QueryNameEnum.QueryFunctionEnum.UPDATE);
        String queryUpdate = FileUtil.getPropertiesFromResources(PATH_RESOURCES_SQL_QUERIES).getProperty(queryNameEnum.getPropertyName());

        try (PreparedStatement preparedStatement = DataSourceConnection.getInstance().getConnection().prepareStatement(queryUpdate)) {
            for (int i = 0; i < params.length; i++) {
                if(params[i] instanceof Timestamp) {
                    preparedStatement.setTimestamp(i + 1, (Timestamp)params[i]);
                }else{
                    preparedStatement.setString(i + 1, (String)params[i]);
                }

            }
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Map<String, Object> selectParameters(ParameterKitEnum parameterKitEnum){

        String queryRead = FileUtil.getPropertiesFromResources(PATH_RESOURCES_SQL_QUERIES).getProperty(QueryNameEnum.SQL_PARAMETERS_SELECT_ALL.getPropertyName());

        Map<String, Object> parametersResult = new HashMap<>();

        try (Connection connection = DataSourceConnection.getInstance().getConnection();
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
                //REFACTORIZAR A GENERICO
                String code = (String)p.get(CODIGO.name());
                if(Objects.nonNull(code)){
                    if(CHUNK_SIZE.name().equals(code)){
                        parametersResult.put(CHUNK_SIZE.name(), p.get(VALOR_NUM.name()));
                    }else if(PORT_NOT.name().equals(code)){
                        parametersResult.put(PORT_NOT.name(), p.get(VALOR_NUM.name()));
                    } else{
                        parametersResult.put(code, p.get(VALOR_ALF.name()));
                    }
                }
            });

        } catch (Exception e) {
            LOGGER.error("Error loading parameters from DB", e);
        }
        LOGGER.info("getJobParameters: parameters = {}", parametersResult);
        return parametersResult;

    }

}
