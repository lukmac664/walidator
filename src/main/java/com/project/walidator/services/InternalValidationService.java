package com.project.walidator.services;

import com.project.walidator.domain.RetrieveValidationResultRequest;
import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.mapper.ValidationRequestMapper;
import com.project.walidator.model.Validation;
import com.project.walidator.repository.ValidationRepository;
import com.project.walidator.ws.impl.SchemaValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class InternalValidationService {

    private final static Logger logger = LogManager.getLogger(InternalValidationService.class);

    @Autowired
    private ValidationRequestMapper validationRequestMapper;

    @Autowired
    private ValidationRepository validationRepository;


    @Autowired
    private SchemaValidator schemaValidator;


    public boolean validate(ValidationRequest validationRequest) {
        return validateDataSchema(validationRequest.getXmlFile(), validationRequest.getXsdFile());
    }

    public void mapToEntityAndPresist(ValidationRequest validationRequest, boolean isValid) {
        Validation validation = null;
        try {
            validation = validationRequestMapper.map(validationRequest, isValid);
            validationRepository.save(validation);
        } catch (SQLException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public Validation findValidationResult(RetrieveValidationResultRequest retrieveValidationResultRequest) {
        return validationRepository.findFirstByMessageIdAndSourceSystemIdAndXsdName(retrieveValidationResultRequest.getMessageId(),
                retrieveValidationResultRequest.getSourceSystemId(), retrieveValidationResultRequest.getXsdName());
    }

    private boolean validateDataSchema(byte[] data, byte[] xsd) {
        return schemaValidator.validate(data, xsd);
    }

}
