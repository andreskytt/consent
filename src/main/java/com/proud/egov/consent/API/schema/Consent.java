package com.proud.egov.consent.API.schema;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;


@XmlType(namespace = "http://proud.com/egov/consent")
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

    public Consent(com.proud.egov.consent.model.Consent dao){
        this.expiry = dao.getExpiry();
        this.ID = dao.getId().toString();
        this.identifiers = new Identifiers(dao.getDataConsumerID(), dao.getUserID(), "");

        ArrayList<Service> services = new ArrayList<>();
        for(com.proud.egov.consent.model.Service service: dao.getServices()){
            services.add(new Service(service.getDataProviderID(), service.getID()));
        }
        this.data = new Data((Service[]) services.toArray(), dao.getHumanReadableConsent());
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
        sb.append(", model=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
