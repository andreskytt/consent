package com.proud.egov.consent;

import javax.annotation.PostConstruct;
import java.util.*;

import com.proud.egov.consent.schema.Consent;
import com.proud.egov.consent.schema.Data;
import com.proud.egov.consent.schema.Identifiers;
import com.proud.egov.consent.schema.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ConsentRepository {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private static final Set<Consent> consents = new HashSet<>();

    @PostConstruct
    public void initData(){
        Service svc = new Service("DPID", "SVCID");

        consents.add(new Consent(
                new Date(),
                "123",
                new Identifiers("DCID", "user", "CCID"),
                new Data(new Service[]{svc}, "I hereby consent")));
    }

    public Consent findConsent(String userID, String DCID, String svcID){
        Assert.notNull(userID, "The userID must not be null");
        Assert.notNull(DCID, "The Data Consumer ID must not be null");
        Assert.notNull(svcID, "The service ID must not be null");

        for(Consent c : consents){
            if(c.getIdentifiers().getUserID().equals(userID) &&
                    c.getIdentifiers().getDCID().equals(DCID)){
                for(Service s : c.getData().getServices()){
                    if(s.getID().equals(svcID)){
                        return c;
                    }
                }
                logger.debug("Found a match for " + userID + "/" + DCID + " but not a service");
            }
        }
        return null;
    }

}
