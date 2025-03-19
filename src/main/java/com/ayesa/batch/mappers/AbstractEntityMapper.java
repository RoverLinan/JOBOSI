package com.ayesa.batch.mappers;

import java.sql.ResultSet;
import java.util.Map;

public abstract class AbstractEntityMapper {

    public abstract Map<String, Object> toEntity(ResultSet resultSet);
}
