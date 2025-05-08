package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.notification.NotificationParameterDTO;

public interface NotificationService {
    void send(NotificationParameterDTO notification);
}
