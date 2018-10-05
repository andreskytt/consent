package com.proud.egov.consent.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Service {
    @Id
    private String ID;
    private String dataProviderID;

}
