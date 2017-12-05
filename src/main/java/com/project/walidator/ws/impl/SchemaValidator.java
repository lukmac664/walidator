package com.project.walidator.ws.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Component
public class SchemaValidator  {
    private final Logger log = LogManager.getLogger(SchemaValidator.class);

    public boolean validate(byte[] input, byte[] xmlSchema) {
        Schema schema = getSchema(xmlSchema);
        try {
            validateXmlAgainstSchema(input, schema);
        } catch (SAXException e) {
            String message = "Given data did not pass schema validation.";
            log.error(message, e);
            return false;
        }
        if (log.isDebugEnabled()) {
            log.debug("Schema validation -> is valid true");
        }
        return true;
    }

    private void validateXmlAgainstSchema(byte[] xml, Schema schema) throws SAXException {
        try (ByteArrayInputStream byteInStream = new ByteArrayInputStream(xml)) {
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(byteInStream));
        } catch (IOException e) {
            String message = "Failed to create InputStream from given xml.";
            System.out.print(message);
        }
    }

    private Schema getSchema(byte[] xsd) {
        try (ByteArrayInputStream byteInStream = new ByteArrayInputStream(xsd)) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return factory.newSchema(new StreamSource(byteInStream));
        } catch (Exception e) {
            String message = "Failed to parse given input as Schema";
            System.out.print(message);
            return null;
        }
    }
}