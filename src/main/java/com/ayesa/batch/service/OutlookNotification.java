package com.ayesa.batch.service;

import com.ayesa.batch.BatchLauncher;
import com.ayesa.batch.business.dto.notification.NotificationParameterDTO;
import com.ayesa.batch.business.dto.notification.MailParameterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.ayesa.batch.enums.JobParameterEnum.URL_NOT;

public class OutlookNotification implements NotificationService {

    @Override
    public void send(NotificationParameterDTO notification) {
        MailParameterDTO outlookParameter = (MailParameterDTO) notification;
        // URL para la API de EmailJS
        final String urlString = (String) BatchLauncher.JOB_PARAMETERS.get(URL_NOT.name());

        // Cuerpo del correo en formato JSON


        try {
            final String payload = new ObjectMapper().writeValueAsString(outlookParameter);
            // Configurar la URL de la API de EmailJS
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Escribir el cuerpo JSON en el flujo de salida
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Obtener la respuesta del servidor
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Correo enviado con éxito.");
            } else {
                System.out.println("Error al enviar el correo. Código de respuesta: " + responseCode);
                System.out.println("Error al enviar el correo. Código de respuesta: " + connection.getResponseMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
