package com.proud.egov.consent.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Service {
    @Id
    private String ID;
    private String dataProviderID;
    private String name;

    @ManyToOne(targetEntity = HumanReadableConsent.class)
    private HumanReadableConsent humanReadableConsent;

    public Service(String ID, String dataProviderID, String name) {
        this.ID = ID;
        this.dataProviderID = dataProviderID;
        this.name = name;
    }

    protected Service() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDataProviderID() {
        return dataProviderID;
    }

    public void setDataProviderID(String dataProviderID) {
        this.dataProviderID = dataProviderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HumanReadableConsent getHumanReadableConsent() {
        return humanReadableConsent;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Service{");
        sb.append("ID='").append(ID).append('\'');
        sb.append(", dataProviderID='").append(dataProviderID).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
