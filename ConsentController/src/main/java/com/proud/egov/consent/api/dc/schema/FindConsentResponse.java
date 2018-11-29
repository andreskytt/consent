package com.proud.egov.consent.api.dc.schema;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "http://proud.com/egov/consent")
@XmlType(namespace = "http://proud.com/egov/consent")
@XmlAccessorType(XmlAccessType.FIELD)
public class FindConsentResponse {
    @XmlElement(required = true)
    private Consent consent;

    // A BASE64-encoded ASiC-E container with serialised com.proud.egov.consent details and their XAdES signature
    @XmlElement
    private String signedConsent;

    public void setConsent(Consent consent) {
        this.consent = consent;
    }

    public void setSignedConsent(String signedConsent) {
        this.signedConsent = signedConsent;
    }
}
