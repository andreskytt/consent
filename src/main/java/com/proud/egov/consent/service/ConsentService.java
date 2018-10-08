package com.proud.egov.consent.service;

import com.proud.egov.consent.model.Consent;

import java.util.List;

public interface ConsentService {
    Consent getConsent(String ID);
    Consent getConsent(String userID, String dataConsumerID);
    Consent getConsent(String userID, String dataConsumerID, String serviceID);
    List getAllConsents(String userID);

}
