package com.proud.egov.consent.API.service;

import com.proud.egov.consent.API.schema.Consent;
import com.proud.egov.consent.API.schema.FindConsentRequest;
import com.proud.egov.consent.API.schema.FindConsentResponse;
import com.proud.egov.consent.API.schema.ServiceFault;
import com.proud.egov.consent.service.ConsentService;
import com.proud.egov.consent.service.ConsentServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ConsentEndpoint {
    private static final String NAMESPACE_URI = "http://proud.com/egov/consent";
    protected final Log logger = LogFactory.getLog(this.getClass());


    private ConsentService consentService;
    private String consentProviderName;

    @Autowired
    public ConsentEndpoint(ConsentServiceImpl consentService, @Value("${com.proud.egov.consent.providerID}") String consentProviderName) {
        this.consentService = consentService;
        this.consentProviderName = consentProviderName;
        logger.info("Consent Provider endpoint started for provider " + consentProviderName);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findConsentRequest")
    @ResponsePayload
    public FindConsentResponse findConsent(@RequestPayload FindConsentRequest request){
        FindConsentResponse response = new FindConsentResponse();
        Consent consent = new Consent(consentService.getConsent(request.getUserID(), request.getDCID(), request.getServiceID()));
        consent.getIdentifiers().setCCID(consentProviderName);

        if(consent != null) {
            response.setConsent(consent);
        }
        else{
            throw new ServiceFaultException("ERROR", new ServiceFault(
                    "NOT_FOUND", "No consent found for user \"" + request.getUserID() + "\"" +
                    " model consumer \"" + request.getDCID() + "\"" +
                    " service \"" + request.getServiceID() + "\""));
        }

        return response;
    }
}
