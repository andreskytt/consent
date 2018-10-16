package com.proud.egov.consent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class HumanReadableConsent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String text;

    public HumanReadableConsent() {
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
