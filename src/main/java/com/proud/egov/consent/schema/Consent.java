package com.proud.egov.consent.schema;

import javax.xml.bind.annotation.*;
import java.util.Date;


@XmlType(namespace = "http://jio.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Consent {
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
        sb.append(", identifiers=").append(identifiers);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
