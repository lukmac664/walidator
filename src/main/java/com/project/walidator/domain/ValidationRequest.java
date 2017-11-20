package com.project.walidator.domain;

import com.project.walidator.model.Priority;
import com.project.walidator.model.SourceSystemId;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationRequest implements Serializable{

    @XmlElement(required = true)
    private SourceSystemId sourceSystemId;
    @XmlElement(required = true)
    private Priority priority;
    @XmlElement(required = true)
    boolean shouldSaveInHistory;
    @XmlElement(required = true)
    private String xsdName;
    @XmlElement(required = true)
    private String messageId;

    public SourceSystemId getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(SourceSystemId sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isShouldSaveInHistory() {
        return shouldSaveInHistory;
    }

    public void setShouldSaveInHistory(boolean shouldSaveInHistory) {
        this.shouldSaveInHistory = shouldSaveInHistory;
    }

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
}
