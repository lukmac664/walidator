package com.project.walidator.ws;

import com.project.walidator.domain.RetrieveValidationResultRequest;
import com.project.walidator.domain.ValidationRequest;
import com.project.walidator.domain.ValidationResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "ValidationService", serviceName = "ValidationService", targetNamespace = "http://www.walidator.com/Validator/ValidationService")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface ValidationService {

    @WebMethod(operationName = "validateXml")
    public ValidationResponse validateXml(@WebParam(name = "validationRequest") ValidationRequest validationRequest);

    @WebMethod(operationName = "findValidationResult")
    public ValidationResponse valifindValidationResultdateXml(@WebParam(name = "RetrieveValidationResultRequest") RetrieveValidationResultRequest retrieveValidationResultRequest);
}
