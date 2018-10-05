package com.proud.egov.consent.API.service;

import com.proud.egov.consent.API.ConsentRepository;
import com.proud.egov.consent.API.schema.Consent;
import com.proud.egov.consent.API.schema.FindConsentRequest;
import com.proud.egov.consent.API.schema.FindConsentResponse;
import com.proud.egov.consent.API.schema.ServiceFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ConsentEndpoint {
    private static final String NAMESPACE_URI = "http://proud.com/egov/consent";

    private ConsentRepository consentRepository;

    @Autowired
    public ConsentEndpoint(ConsentRepository consentRepository) {
        this.consentRepository = consentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findConsentRequest")
    @ResponsePayload
    public FindConsentResponse findConsent(@RequestPayload FindConsentRequest request){
        FindConsentResponse response = new FindConsentResponse();
        Consent consent = consentRepository.findConsent(request.getUserID(), request.getDCID(), request.getServiceID());

        if(consent != null) {
            response.setConsent(consent);
        }
        else{
            throw new ServiceFaultException("ERROR", new ServiceFault(
                    "NOT_FOUND", "No consent found for user \"" + request.getUserID() + "\"" +
                    " data consumer \"" + request.getDCID() + "\"" +
                    " service \"" + request.getServiceID() + "\""));
        }

        return response;
    }
}