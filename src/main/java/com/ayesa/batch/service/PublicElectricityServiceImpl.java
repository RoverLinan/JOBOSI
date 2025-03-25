package com.ayesa.batch.service;

import com.ayesa.batch.business.dto.osinergmin.AbstractResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableCatalogResponseDTO;
import com.ayesa.batch.business.dto.osinergmin.TableStructureResponseDTO;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class PublicElectricityServiceImpl implements PublicElectricityService{


    private static final Logger LOGGER = LoggerFactory.getLogger(PublicElectricityServiceImpl.class);


    @Override
    public TableCatalogResponseDTO getTableCatalog() {
        return null;
    }

    @Override
    public TableStructureResponseDTO getTableStructure(String tableCode) {
        return null;
    }

    @Override
    public AbstractResponseDTO submitInformationForProcessing(final String filePath) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {


            String url = "/api/upload";


            HttpPost post = new HttpPost(url);


            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("field1", new StringBody("value1", ContentType.MULTIPART_FORM_DATA));
            builder.addPart("field2", new StringBody("value2", ContentType.MULTIPART_FORM_DATA));


            File file = new File("/path/to/file.txt");
            builder.addPart("file", new FileBody(file));


            HttpEntity entity = builder.build();
            post.setEntity(entity);



            CloseableHttpResponse response = client.execute(post);


            String responseString = EntityUtils.toString (response.getEntity());
            LOGGER.info("submitInformationForProcessing: response = {}", responseString);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public AbstractResponseDTO confirmInformationSubmission() {
        return null;
    }

    @Override
    public AbstractResponseDTO revertInformationConfirmation() {
        return null;
    }


}
