package com.ayesa.batch.steps;

import com.ayesa.batch.enums.JobNameEnum;
import com.ayesa.batch.util.FileUtil;


import java.util.List;
import java.util.Map;

public class DataProcessor {
    private final JobNameEnum jobNameEnum;

    public DataProcessor(JobNameEnum jobNameEnum) {
        this.jobNameEnum = jobNameEnum;
    }

    public void process(List<Map<String, Object>> data, String fileName) {
        FileUtil.writeMapToFile(data, fileName, FileUtil.DELIMITER_VALUES_UPLOAD);
    }
}
