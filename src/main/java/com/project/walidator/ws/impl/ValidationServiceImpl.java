package com.project.walidator.ws.impl;

import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.domain.ValidationResponse;
import com.project.walidator.mapper.ValidationRequestMapper;
import com.project.walidator.model.Validation;
import com.project.walidator.model.ValidationResult;
import com.project.walidator.repository.ValidationRepository;
import com.project.walidator.ws.ValidationService;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;


@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private SchemaValidator schemaValidator;

    @Autowired
    private ValidationRequestMapper validationRequestMapper;

    @Autowired
    private ValidationRepository validationRepository;

    @Override
    @Transactional

    public ValidationResponse validateXml(ValidationRequest validationRequest) {

        boolean isValid = validateDataSchema(validationRequest.getXmlFile(), validationRequest.getXsdFile());
        Validation validation =null;
        try {
            validation = validationRequestMapper.map(validationRequest, isValid);
            validationRepository.save(validation);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //persist to db if requested (map to entities and save)

        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setValidationResult(isValid ? ValidationResult.VALID : ValidationResult.NOT_VALID);

        return validationResponse;
    }

    private boolean validateDataSchema(byte[] data, byte[] xsd) {
        return schemaValidator.validate(data, xsd);
    }

    private byte[] prepareSchemaAsByteArray(String schemaDirName) {
        String schemaLocation = schemaDirName + "/BinaryDataSchema.xsd";
        try (FileInputStream fis = new FileInputStream(new File(schemaLocation))) {
            return IOUtils.readBytesFromStream(fis);
        } catch (IOException | NullPointerException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
