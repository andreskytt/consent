package com.proud.egov.consent.API.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFault")
public class ServiceFault {
    private String errorCode;
    private String description;

    public ServiceFault(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public ServiceFault() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
