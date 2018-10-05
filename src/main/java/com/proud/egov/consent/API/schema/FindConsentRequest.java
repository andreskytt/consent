package com.proud.egov.consent.API.schema;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "http://proud.com/egov/consent")
@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)
public class FindConsentRequest {
    @XmlElement(required = true)
    private String userID;

    @XmlElement(required = true)
    private String DCID;

    @XmlElement(required = true)
    private String serviceID;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FindConsentRequest{");
        sb.append("userID='").append(userID).append('\'');
        sb.append(", DCID='").append(DCID).append('\'');
        sb.append(", serviceID='").append(serviceID).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUserID() {
        return userID;
    }

    public String getDCID() {
        return DCID;
    }

    public String getServiceID() {
        return serviceID;
    }
}
