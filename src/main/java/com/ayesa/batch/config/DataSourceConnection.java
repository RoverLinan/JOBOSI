package com.ayesa.batch.config;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.exception.LogicalException;
import com.ayesa.batch.util.FileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DataSourceConnection {

    private static DataSourceConnection dataSourceConnection;
    private final Connection connection;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;


    private DataSourceConnection() {
        Properties properties = FileUtil.loadProperties((String) BatchLauncher.JOB_PARAMETERS.get(BatchLauncher.FILE_CREDENTIALS_BD));
        this.dbUrl = properties.getProperty("db.url");
        this.dbUsername = properties.getProperty("db.username");
        this.dbPassword = properties.getProperty("db.password");

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new LogicalException("002",Boolean.FALSE,"Error DataSource",e);
        }

    }

    public static synchronized DataSourceConnection getInstance() {

        if(Objects.isNull(dataSourceConnection)){
           dataSourceConnection = new DataSourceConnection();
        }
        return dataSourceConnection;
    }

    public Connection getConnection(){
        return this.connection;
    }

}
