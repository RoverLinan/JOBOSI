package com.ayesa.batch.repository;

import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.util.FileUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class TableRepository {
    private TableRepository(){}
    public static int update(JobNameEnum jobName, String... params) {
        QueryNameEnum queryNameEnum = QueryNameEnum.inverse(jobName, QueryNameEnum.QueryFunctionEnum.UPDATE);
        String queryUpdate = FileUtil.getPropertiesFromResources().getProperty(queryNameEnum.getPropertyName());

        try (PreparedStatement preparedStatement = DataSourceConnection.getInstance().getConnection().prepareStatement(queryUpdate)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
