package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class PublicElectricityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicElectricityServiceTest.class);
    @Before
    public void setUp() throws Exception {
    }



    @Test
    public void testGetTableCatalog() throws JsonProcessingException {
       String body = "{\n" +
               "    \"listaErrores\": [\n" +
               "        {\n" +
               "            \"codigo\": \"E0001\",\n" +
               "            \"linea\": 1,\n" +
               "            \"descripcion\": \"La Fecha de notificacion de respuesta: \\r no es valida (Formato: dd/mm/aaaa hh:mm)\"\n" +
               "        }\n" +
               "    ],\n" +
               "    \"valorResultante\": 0,\n" +
               "    \"codigoMensaje\": \"OSI-302\",\n" +
               "    \"mensajeResultante\": \"Errores al procesar filas\"\n" +
               "}";
        body = body.replace("\\r", " "); // Normalize line endings
        AbstractResponseDTO abstractResponseDTO = new ObjectMapper().readValue(body, AbstractResponseDTO.class);
        LOGGER.info("AbstractResponseDTO: {}", abstractResponseDTO);
        assertNotNull(abstractResponseDTO);

    }
}