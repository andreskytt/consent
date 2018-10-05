package com.proud.egov.consent.API.schema;

import javax.xml.bind.annotation.*;

@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElement(required = true)
    private Service[] services;

    /*
    The detailed explanation of the service the user has read and accepted.
    Can be either plain text or a BASE64-encoded PDF document
    */
    @XmlElement(required = true)
    private String humanReadableConsent;

    public Data() {
    }

    public Data(Service[] services, String humanReadableConsent) {
        this.services = services;
        this.humanReadableConsent = humanReadableConsent;
    }

    public Service[] getServices() {
        return services;
    }

    public void setServices(Service[] services) {
        this.services = services;
    }

    public String getHumanReadableConsent() {
        return humanReadableConsent;
    }

    public void setHumanReadableConsent(String humanReadableConsent) {
        this.humanReadableConsent = humanReadableConsent;
    }
}
