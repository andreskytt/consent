package com.proud.egov.consent.service;


import com.proud.egov.consent.model.Consent;
import com.proud.egov.consent.model.Service;
import com.proud.egov.consent.repository.ConsentRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.util.Assert;

import java.util.List;


@org.springframework.stereotype.Service
public class ConsentServiceImpl implements ConsentService{
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ConsentRepository consentRepository;

    @Override
    public Consent getConsent(String ID) {
        return null;
    }

    @Override
    public Consent getConsent(String userID, String dataConsumerID) {
        return null;
    }

    @Override
    public List<Consent> getAllConsents(String userID) {
        return null;
    }

    @Override
    public Consent getConsent(String userID, String dataConsumerID, String serviceID) {
        Assert.notNull(userID, "The userID must not be null");
        Assert.notNull(dataConsumerID, "The Data Consumer ID must not be null");
        Assert.notNull(serviceID, "The service ID must not be null");

        logger.debug("Entering getConsent, going to execute a query");
        Example<Consent> consentExample = Example.of(new Consent(dataConsumerID, userID));
        List<Consent> consents = consentRepository.findAll(consentExample);
        logger.debug("Query done, found " + consents.size() + " records");

        for(Consent c : consents){
            if(c.getService().getID().equals(serviceID)){
                return c;
            }
        }
        return null;
    }
}
