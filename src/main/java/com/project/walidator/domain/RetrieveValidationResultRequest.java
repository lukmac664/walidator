package com.project.walidator.domain;

import com.project.walidator.model.SourceSystemId;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RetrieveValidationResultRequest implements Serializable {

    @XmlElement(required = true)
    private String xsdName;
    @XmlElement(required = true)
    private String messageId;
    @XmlElement(required = true)
    private SourceSystemId sourceSystemId;

    public String getXsdName() {
        return xsdName;
    }

    public void setXsdName(String xsdName) {
        this.xsdName = xsdName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public SourceSystemId getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(SourceSystemId sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }
}
