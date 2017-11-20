package com.project.walidator.ws.impl;

import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.domain.ValidationResponse;
import com.project.walidator.ws.ValidationService;


public class ValidationServiceImpl implements ValidationService {
    @Override
    public ValidationResponse validateXml(ValidationRequest validationRequest) {
        //validate if request is correct

        //perform xml validation against xsd schema


        //persist to db if requested (map to entities and save)


        return null;
    }
}
