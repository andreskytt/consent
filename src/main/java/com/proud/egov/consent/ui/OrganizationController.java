package com.proud.egov.consent.ui;

import com.proud.egov.consent.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/organizations.json")

public class OrganizationController {
    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping
    public Iterable findAll(){return organizationRepository.findAll();}
}
