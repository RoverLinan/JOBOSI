package com.ayesa.batch.steps;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.exception.LogicalException;
import com.ayesa.batch.config.DataSourceConnection;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.QueryNameEnum;
import com.ayesa.batch.mappers.AbstractEntityMapper;
import com.ayesa.batch.mappers.EntityMapperCreator;
import com.ayesa.batch.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ayesa.batch.BatchLauncher.JOB_PARAMETERS;
import static com.ayesa.batch.enums.JobParameterEnum.CHUNK_SIZE;
import static com.ayesa.batch.enums.JobParameterEnum.PERIODO_REMISION;
import static com.ayesa.batch.util.DateUtil.FORMAT_DATETIME_3;
import static com.ayesa.batch.util.FileUtil.PATH_RESOURCES_SQL_QUERIES;


public class DataReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataReader.class);
    public static int TOTAL_ELEMENTS;
    public static int TOTAL_BLOCKS;

    private final JobNameEnum jobName;

    private final DataSourceConnection dataSourceConnection;

    public DataReader(JobNameEnum jobName) {
        this.dataSourceConnection = DataSourceConnection.getInstance();
        this.jobName = jobName;
    }

    public List<Map<String, Object>> read(int offset, int chunkSize) {
        LOGGER.info("read: offset = {}, chunkSize = {}", offset, chunkSize);
        QueryNameEnum queryNameEnum = QueryNameEnum.inverse(jobName, QueryNameEnum.QueryFunctionEnum.SELECT);
        String queryRead = FileUtil.getPropertiesFromResources(PATH_RESOURCES_SQL_QUERIES).getProperty(queryNameEnum.getPropertyName());
        List<Map<String, Object>> data = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.dataSourceConnection.getConnection().prepareStatement(queryRead)) {
            LocalDate period = (LocalDate) JOB_PARAMETERS.get(PERIODO_REMISION.name());

            preparedStatement.setString(1, period.plusDays(1).toString() );
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, chunkSize);


            LOGGER.info("read: query = {}", queryRead);
            try (ResultSet result = preparedStatement.executeQuery()) {
                AbstractEntityMapper entityMapper = EntityMapperCreator.create(jobName);
                while (result.next()) {
                    data.add(entityMapper.toEntity(result));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("read: data = {}", data);
        return data;
    }

    public void countElements() {
        QueryNameEnum queryNameEnum = QueryNameEnum.inverse(jobName, QueryNameEnum.QueryFunctionEnum.COUNT);
        String queryCount = FileUtil.getPropertiesFromResources(PATH_RESOURCES_SQL_QUERIES).getProperty(queryNameEnum.getPropertyName());
        LOGGER.info("countElements: query count = {} ", queryCount);

        try (PreparedStatement preparedStatement = this.dataSourceConnection.getConnection().prepareStatement(queryCount)){
             LocalDate period = (LocalDate) JOB_PARAMETERS.get(PERIODO_REMISION.name());

             preparedStatement.setString(1, period.plusDays(1).toString() );
             ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                TOTAL_ELEMENTS = result.getInt("TOTAL");
            }
        } catch (SQLException sql) {
            throw new LogicalException("002", Boolean.FALSE, sql.getLocalizedMessage(), sql);

        }
        calculateBlocks();
        LOGGER.info("countElements: result count = {}", TOTAL_ELEMENTS);
    }

    private void calculateBlocks() {
        TOTAL_BLOCKS = (int) Math.ceil((double) TOTAL_ELEMENTS / (int) JOB_PARAMETERS.get(CHUNK_SIZE.name()));
    }
}
