package com.ayesa.batch.mappers;

import com.ayesa.batch.business.exception.LogicalException;
import com.ayesa.batch.enums.JobNameEnum;

public class EntityMapperCreator {

    private EntityMapperCreator(){}

    public static AbstractEntityMapper create(JobNameEnum jobNameEnum){
        AbstractEntityMapper abstractEntityMapper;
        switch (jobNameEnum){
            case JOB01: abstractEntityMapper = new Table1Mapper(); break;
            case JOB02: abstractEntityMapper = new Table2Mapper(); break;
            case JOB03: abstractEntityMapper = new Table3Mapper(); break;
            case JOB04: abstractEntityMapper = new Table4Mapper(); break;
            case JOB05: abstractEntityMapper = new Table5Mapper(); break;
            case JOB06: abstractEntityMapper = new Table6Mapper(); break;
            case JOB07: abstractEntityMapper = new Table7Mapper(); break;
            case JOB08: abstractEntityMapper = new Table8Mapper(); break;
            default:
                throw new LogicalException("003",
                        false,
                        EntityMapperCreator.class.getSimpleName().concat(" jobName no encontrado"),
                        new IllegalArgumentException(jobNameEnum.toString())
                );
        }


        return abstractEntityMapper;
    }
}
