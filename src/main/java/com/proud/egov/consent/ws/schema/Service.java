package com.proud.egov.consent.ws.schema;

import javax.xml.bind.annotation.*;

@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)

public class Service {
    @XmlAttribute(required = true)
    private String ID;

    @XmlElement(required = true)
    private String dataProviderID;

    @XmlElement(required = true)
    private String name;

    public Service() {
    }

    public Service(String ID, String dataProviderID, String name) {
        this.ID = ID;
        this.dataProviderID = dataProviderID;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
