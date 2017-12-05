package com.project.walidator.ws.impl;

import com.project.walidator.domain.RetrieveValidationResultRequest;
import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.domain.ValidationResponse;
import com.project.walidator.model.Validation;
import com.project.walidator.model.ValidationResult;
import com.project.walidator.services.InternalValidationService;
import com.project.walidator.ws.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private InternalValidationService internalValidationService;

    @Override
    @Transactional
    public ValidationResponse validateXml(ValidationRequest validationRequest) {
        boolean isValid = internalValidationService.validate(validationRequest);
        internalValidationService.mapToEntityAndPresist(validationRequest, isValid);
        return prepareResponse(isValid);
    }

    @Override
    public ValidationResponse valifindValidationResultdateXml(RetrieveValidationResultRequest retrieveValidationResultRequest) {
        Validation validation = internalValidationService.findValidationResult(retrieveValidationResultRequest);
        return prepareResponse(validation.isValid());
    }

    private ValidationResponse prepareResponse(boolean isValid) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setValidationResult(isValid ? ValidationResult.VALID : ValidationResult.NOT_VALID);
        return validationResponse;
    }

}
