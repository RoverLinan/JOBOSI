package com.ayesa.batch.business.dto.notification;

import java.io.Serializable;

public class DetailErrorMailDTO implements Serializable {

    private String attentionId;
    private String actionId;
    private String description;


    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "DetailErrorMailDTO{" +
                "attentionId='" + attentionId + '\'' +
                ", actionId='" + actionId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
