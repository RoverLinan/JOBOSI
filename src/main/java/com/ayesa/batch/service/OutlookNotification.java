package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.ayesa.batch.business.dto.notification.NotificationParameterDTO;
import com.ayesa.batch.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OutlookNotification implements NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutlookNotification.class);
    @Override
    public void send(NotificationParameterDTO notification) {
        MailParameterDTO outlookParameter = (MailParameterDTO) notification;

        String result =  NotificationRepository.executePackageMailNotification(outlookParameter);

        LOGGER.info("send - Resultado del envío de correo: {}", result);
    }

}
