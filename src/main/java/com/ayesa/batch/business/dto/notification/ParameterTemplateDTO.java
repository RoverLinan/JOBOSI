package com.ayesa.batch.business.dto.notification;

import java.io.Serializable;
import java.util.List;

public class ParameterTemplateDTO implements Serializable {
    private String title;
    private String mailTo;
    private String tableName;
    private String countErrors;
    private String countProcessed;
    private String date;
    private String userId;
    private String period;

    private List<DetailErrorMailDTO> details;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCountErrors() {
        return countErrors;
    }

    public void setCountErrors(String countErrors) {
        this.countErrors = countErrors;
    }

    public String getCountProcessed() {
        return countProcessed;
    }

    public void setCountProcessed(String countProcessed) {
        this.countProcessed = countProcessed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<DetailErrorMailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<DetailErrorMailDTO> details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "ParameterTemplateDTO{" +
                "title='" + title + '\'' +
                ", mailTo='" + mailTo + '\'' +
                ", tableName='" + tableName + '\'' +
                ", countErrors='" + countErrors + '\'' +
                ", countProcessed='" + countProcessed + '\'' +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                ", period='" + period + '\'' +
                ", details=" + details +
                '}';
    }
}
