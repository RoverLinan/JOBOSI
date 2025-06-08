package com.ayesa.batch.repository;

import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.util.FileUtil;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import static com.ayesa.batch.util.FileUtil.PATH_RESOURCES_SQL_QUERIES;

public class NotificationRepository {


    public static String executePackageMailNotification(MailParameterDTO mailParameterDTO) {


        String packageCall = FileUtil.getPropertiesFromResources(PATH_RESOURCES_SQL_QUERIES).getProperty(QueryNameEnum.SQL_PQ_CORREO_NOTIFICATION.getPropertyName());
        String result = "";
        try (CallableStatement callableStatement = DataSourceConnection.getInstance().getConnection().prepareCall(packageCall)) {

            callableStatement.setString(1, mailParameterDTO.getHost());
            callableStatement.setInt(2, mailParameterDTO.getPort());
            callableStatement.setString(3, mailParameterDTO.getMailFrom());
            callableStatement.setString(4, mailParameterDTO.getMailTo());
            callableStatement.setString(5, mailParameterDTO.getCc());
            callableStatement.setString(6, mailParameterDTO.getSubject());
            callableStatement.setString(7, mailParameterDTO.getMessage());
            callableStatement.setString(8, null);
            callableStatement.setString(9, mailParameterDTO.getType());
            callableStatement.setInt(10, mailParameterDTO.getOption());
            callableStatement.registerOutParameter(11, Types.VARCHAR);

            callableStatement.execute();

            result = callableStatement.getString(11);
            if (result != null && !result.isEmpty()) {
                System.out.println("Resultado del paquete: " + result);
            } else {
                System.out.println("El paquete se ejecutó correctamente sin errores.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el paquete: " + e.getMessage());
        }
        return "Paquete ejecutado" + result;
    }
}
