package com.proud.egov.consent.api.dc.service;

import com.google.gson.Gson;
import com.proud.egov.consent.api.dc.schema.FindConsentRequest;
import com.proud.egov.consent.api.dc.schema.FindConsentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestConsentController {
    @Autowired
    private ConsentEndpoint endpoint;

    @GetMapping("api/dc/consents/{userID}/{DCID}/{serviceID}")
    public String findConsent(@PathVariable(value="userID") String userID,
                                           @PathVariable(value="DCID") String DCID,
                                           @PathVariable(value="serviceID") String serviceID){
        FindConsentRequest request = new FindConsentRequest();
        request.setUserID(userID);
        request.setDCID(DCID);
        request.setServiceID(serviceID);
        FindConsentResponse consent = endpoint.findConsent(request);

        Gson gson = new Gson();

        return gson.toJson(consent);
    }

}

