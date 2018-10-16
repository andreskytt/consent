package com.proud.egov.consent.ws.service;

import com.proud.egov.consent.ws.schema.Consent;
import com.proud.egov.consent.ws.schema.FindConsentRequest;
import com.proud.egov.consent.ws.schema.FindConsentResponse;
import com.proud.egov.consent.ws.schema.ServiceFault;
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

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

        com.proud.egov.consent.model.Consent modelConsent = consentService.getConsent(request.getUserID(), request.getDCID(), request.getServiceID());

        if(modelConsent == null){
            throw new ServiceFaultException("ERROR", new ServiceFault(
                    "NOT_FOUND", "No consent found for user \"" + request.getUserID() + "\"" +
                    " model consumer \"" + request.getDCID() + "\"" +
                    " service \"" + request.getServiceID() + "\""));
        }

        Consent consent = new Consent(modelConsent);
        consent.getIdentifiers().setCCID(consentProviderName);

        response.setSignedConsent(
                new String(
                        Base64.getEncoder().encode(modelConsent.getSignedContainer())));
        response.setConsent(consent);
        return response;
    }
}
