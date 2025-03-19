package com.ayesa.batch.business.exception;

public class LogicalException extends RuntimeException{

    private final String code;
    private final boolean rollBack;
    private final String description;

    private final Exception exception;


    public LogicalException(String code, boolean rollBack, String description, Exception exception) {
        this.code = code;
        this.rollBack = rollBack;
        this.description = description;
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }


    public boolean isRollBack() {
        return rollBack;
    }


    public String getDescription() {
        return description;
    }


    public Exception getException() {
        return exception;
    }



    @Override
    public String toString() {
        return "LogicalException{" +
                "code='" + code + '\'' +
                ", rollBack=" + rollBack +
                ", description='" + description + '\'' +
                ", exception=" + exception +
                "} " + super.toString();
    }
}
