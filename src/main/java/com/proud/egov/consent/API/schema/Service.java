package com.proud.egov.consent.API.schema;

import javax.xml.bind.annotation.*;

@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)

public class Service {
    @XmlAttribute(required = true)
    private String ID;

    @XmlElement(required = true)
    private String dataProviderID;


    public Service() {
    }

    public Service(String dataProviderID, String ID) {
        this.dataProviderID = dataProviderID;
        this.ID = ID;
    }

    public String getDataProviderID() {
        return dataProviderID;
    }

    public void setDataProviderID(String dataProviderID) {
        this.dataProviderID = dataProviderID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
