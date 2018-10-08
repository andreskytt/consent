package com.proud.egov.consent.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Service {
    @Id
    private String ID;
    private String dataProviderID;

    public Service(String ID, String dataProviderID) {
        this.ID = ID;
        this.dataProviderID = dataProviderID;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Service{");
        sb.append("ID='").append(ID).append('\'');
        sb.append(", dataProviderID='").append(dataProviderID).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
