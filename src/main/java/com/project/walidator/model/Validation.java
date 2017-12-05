package com.project.walidator.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "VALIDATIONS")
public class Validation {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOURCE_SYSTEM_ID")
    private SourceSystemId sourceSystemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private Priority priority;

    @Column(name = "XSD_NAME")
    private String xsdName;

    @Column(name = "MESSAGE_ID")
    private String messageId;

    @Column( name ="IS_VALID")
    boolean isValid;

    @Lob
    @Column(name = "XSD_BLOB")
    private Blob xsdFile;

    @Lob
    @Column(name = "XML_BLOB")
    private Blob xmlFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Blob getXsdFile() {
        return xsdFile;
    }

    public void setXsdFile(Blob xsdFile) {
        this.xsdFile = xsdFile;
    }

    public Blob getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(Blob xmlFile) {
        this.xmlFile = xmlFile;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
