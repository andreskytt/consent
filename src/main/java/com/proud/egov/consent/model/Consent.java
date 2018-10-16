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

    @Lob
    private byte[] SignedContainer;

    //Identifiers
    private String dataConsumerID;
    private String userID;

    //Data
    @ManyToOne(targetEntity = Service.class)
    private Service service;

    @ManyToOne(targetEntity = HumanReadableConsent.class)
    private HumanReadableConsent humanReadableConsent;

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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public HumanReadableConsent getHumanReadableConsent() {
        return humanReadableConsent;
    }

    public void setHumanReadableConsent(HumanReadableConsent humanReadableConsent) {
        this.humanReadableConsent = humanReadableConsent;
    }

    public byte[] getSignedContainer() {
        return SignedContainer;
    }

    public void setSignedContainer(byte[] signedContainer) {
        SignedContainer = signedContainer;
    }

    // Used in constructing examples
    public Consent(String dataConsumerID, String userID) {
        this.dataConsumerID = dataConsumerID;
        this.userID = userID;
    }

    public Consent() {
    }
}
