package com.ayesa.batch.repository;

import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.util.FileUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TableRepository {
    private TableRepository(){}
    public static int update(JobNameEnum jobName, Object... params) {
        QueryNameEnum queryNameEnum = QueryNameEnum.inverse(jobName, QueryNameEnum.QueryFunctionEnum.UPDATE);
        String queryUpdate = FileUtil.getPropertiesFromResources().getProperty(queryNameEnum.getPropertyName());

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

}
