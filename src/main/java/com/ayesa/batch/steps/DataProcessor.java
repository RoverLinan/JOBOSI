package com.ayesa.batch.steps;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.enums.JobParameterEnum;
import com.ayesa.batch.mappers.AbstractEntityMapper;
import com.ayesa.batch.util.FileUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DataProcessor {
    private final JobNameEnum jobNameEnum;

    public DataProcessor(JobNameEnum jobNameEnum) {
        this.jobNameEnum = jobNameEnum;
    }

    public void process(List<Map<String, Object>> data, String fileName) {
        final String delimiter = (String) BatchLauncher.JOB_PARAMETERS.get(JobParameterEnum.CHAR_DELIM.name());
        FileUtil.writeMapToFile(data, fileName, delimiter);
    }

    public List<Serializable> process(List<Map<String, Object>> data, AbstractEntityMapper mapper) {
        return mapper.toListDTO(data);
    }
}
