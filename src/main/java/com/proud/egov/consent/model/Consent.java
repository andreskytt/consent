package com.proud.egov.consent.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Consent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Date expiry;

    //Identifiers
    private String dataConsumerID;
    private String userID;

    //Data
    @OneToMany(targetEntity = Service.class)
    private List<Service> services;
    private String humanReadableConsent;

    public UUID getId() {
        return id;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getDataConsumerID() {
        return dataConsumerID;
    }

    public void setDataConsumerID(String dataConsumerID) {
        this.dataConsumerID = dataConsumerID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getHumanReadableConsent() {
        return humanReadableConsent;
    }

    public void setHumanReadableConsent(String humanReadableConsent) {
        this.humanReadableConsent = humanReadableConsent;
    }

    // Used in constructing examples
    public Consent(String dataConsumerID, String userID) {
        this.dataConsumerID = dataConsumerID;
        this.userID = userID;
    }
}
