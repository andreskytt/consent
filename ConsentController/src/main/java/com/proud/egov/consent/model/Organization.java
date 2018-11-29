package com.proud.egov.consent.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Organization {
    @Id
    private String ID;
    private String fullName;

    public Organization() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
