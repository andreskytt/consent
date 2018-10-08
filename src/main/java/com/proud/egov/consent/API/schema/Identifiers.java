package com.proud.egov.consent.API.schema;

import javax.xml.bind.annotation.*;

@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Identifiers {

    // An identifier of the model consumer (service provider) the consent is given out to
    @XmlElement(required = true)
    private String DCID;

    // An identifier of the user giving the consent. It must match the identifier
    // in the signature of the signed consent container
    @XmlElement(required = true)
    private String userID;

    // An identifier of the consent collector. It must match the identifier supplied
    // in the non-repudiation mechanism of the communications channel
    @XmlElement(required = true)
    private String CCID;

    public Identifiers(String DCID, String userID, String CCID) {
        this.DCID = DCID;
        this.userID = userID;
        this.CCID = CCID;
    }

    public Identifiers() {
    }


    public String getDCID() {
        return DCID;
    }

    public void setDCID(String DCID) {
        this.DCID = DCID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCCID() {
        return CCID;
    }

    public void setCCID(String CCID) {
        this.CCID = CCID;
    }
}

