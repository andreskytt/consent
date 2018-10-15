package com.proud.egov.consent.ws.schema;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Consent {
    @XmlTransient
    protected final Log logger = LogFactory.getLog(this.getClass());

    @XmlAttribute(required = true)
    private Date expiry;

    @XmlAttribute(required = true)
    private String ID;

    @XmlElement(required = true)
    private Identifiers identifiers;

    @XmlElement(required = true)
    private Data data;


    public Consent() {
    }

    public Consent(Date expiry, String ID, Identifiers identifiers, Data data) {
        this.expiry = expiry;
        this.ID = ID;
        this.identifiers = identifiers;
        this.data = data;
    }

    public Consent(com.proud.egov.consent.model.Consent dao){
        this.expiry = dao.getExpiry();
        this.ID = dao.getId().toString();
        this.identifiers = new Identifiers(dao.getDataConsumerID(), dao.getUserID(), "");

        ArrayList<Service> services = new ArrayList<>();
        for(com.proud.egov.consent.model.Service service: dao.getServices()){
            services.add(new Service(service.getID(), service.getDataProviderID(), service.getName()));
        }
        this.data = new Data(services.toArray(new Service[0]), dao.getHumanReadableConsent());
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Identifiers getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Consent{");
        sb.append("expiry=").append(expiry);
        sb.append(", ID='").append(ID).append('\'');
        sb.append(", identifiers=").append(identifiers.toString());
        sb.append(", model=").append(data.toString());
        sb.append('}');
        return sb.toString();
    }

    @XmlType(namespace = "http://proud.com/egov/consent")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Data {
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

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Data{");
            sb.append("services=").append(services == null ? "null" : Arrays.asList(services).toString());
            sb.append(", humanReadableConsent='").append(humanReadableConsent).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @XmlType(namespace = "http://proud.com/egov/consent")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Identifiers {

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

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Identifiers{");
            sb.append("DCID='").append(DCID).append('\'');
            sb.append(", userID='").append(userID).append('\'');
            sb.append(", CCID='").append(CCID).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
