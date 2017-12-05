package com.project.walidator.mapper;

import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.model.Validation;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ValidationRequestMapper {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    public Validation map(ValidationRequest validationRequest, boolean isValid) throws SQLException, IOException {
        Validation validation = new Validation();
        validation.setMessageId(validationRequest.getMessageId());
        validation.setPriority(validationRequest.getPriority());
        validation.setSourceSystemId(validationRequest.getSourceSystemId());
        validation.setValid(isValid);
        validation.setXsdName(validationRequest.getXsdName());
        validation.setXmlFile(createBlob(new ByteArrayInputStream(validationRequest.getXmlFile())));
        validation.setXsdFile(createBlob(new ByteArrayInputStream(validationRequest.getXsdFile())));
        return validation;
    }

    public Blob createBlob(InputStream inputStream) throws SQLException, IOException {
        Connection conn = DataSourceUtils.getConnection(dataSource);

        Blob blob = conn.createBlob();

        try (OutputStream dbOut = blob.setBinaryStream(1)) {
            StreamUtils.copy(inputStream, dbOut, 4096);
        }

        return blob;
    }
}
