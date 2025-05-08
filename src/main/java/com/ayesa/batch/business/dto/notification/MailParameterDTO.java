package com.ayesa.batch.business.dto.notification;

import java.util.List;

public class MailParameterDTO extends NotificationParameterDTO {
    private String service_id;
    private String template_id;
    private String user_id;
    private String accessToken;
    private ParameterTemplateDTO template_params;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public ParameterTemplateDTO getTemplate_params() {
        return template_params;
    }

    public void setTemplate_params(ParameterTemplateDTO template_params) {
        this.template_params = template_params;
    }

    @Override
    public String toString() {
        return "MailParameterDTO{" +
                "service_id='" + service_id + '\'' +
                ", template_id='" + template_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", template_params=" + template_params +
                "} " + super.toString();
    }
}
