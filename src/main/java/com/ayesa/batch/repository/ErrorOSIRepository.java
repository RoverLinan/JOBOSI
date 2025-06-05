package com.ayesa.batch.repository;

import com.ayesa.batch.business.bo.ErrorOSIBO;
import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.enums.StatusEnum;
import com.ayesa.batch.util.FileUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.ayesa.batch.BatchLauncher.JOB_PARAMETERS;
import static com.ayesa.batch.enums.JobParameterEnum.OSI_USER;
import static com.ayesa.batch.util.FileUtil.PATH_RESOURCES_SQL_QUERIES;

public class ErrorOSIRepository {


    public static void insert(ErrorOSIBO errorOSIBO) {


        String queryUpdate = FileUtil.getPropertiesFromResources(PATH_RESOURCES_SQL_QUERIES).getProperty(QueryNameEnum.SQL_ERROR_INSERT.getPropertyName());

        try (PreparedStatement preparedStatement = DataSourceConnection.getInstance().getConnection().prepareStatement(queryUpdate)) {

            preparedStatement.setString(1, errorOSIBO.getUuid());
            preparedStatement.setString(2, errorOSIBO.getNombreTabla());
            preparedStatement.setString(3, errorOSIBO.getCodAtencion());
            preparedStatement.setString(4, errorOSIBO.getCodAccion());
            preparedStatement.setString(5, errorOSIBO.getMensajeError());
            preparedStatement.setString(6, errorOSIBO.getTipoError());
            preparedStatement.setString(7, errorOSIBO.getCampoError());
            preparedStatement.setString(8, errorOSIBO.getCodigoErrorExterno());
            preparedStatement.setTimestamp(9, Timestamp.valueOf(errorOSIBO.getFechaError()));
            preparedStatement.setString(10, StatusEnum.PENDIENTE.name());
            preparedStatement.setString(11, errorOSIBO.getComentariosAdicionales());
            preparedStatement.setString(12, (String) JOB_PARAMETERS.get(OSI_USER.name()));

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
