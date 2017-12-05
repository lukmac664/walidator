package com.project.walidator.ws.impl;

import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.domain.ValidationResponse;
import com.project.walidator.model.ValidationResult;
import com.project.walidator.ws.ValidationService;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private SchemaValidator schemaValidator;

    @Override
    public ValidationResponse validateXml(ValidationRequest validationRequest) {
        //validate if request is correct

        //perform xml validation against xsd schema

        validateDataSchema(validationRequest.getXmlFile(), validationRequest.getXsdFile());

        //persist to db if requested (map to entities and save)

        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setValidationResult(ValidationResult.VALID);

        return validationResponse;
    }

    private void validateDataSchema(byte[] data, byte[] xsd) {
        String schemaDirName = "test.xsd";

        schemaValidator.validate(data, xsd);
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
