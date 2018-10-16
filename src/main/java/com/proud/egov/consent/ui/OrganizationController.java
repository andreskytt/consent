package com.proud.egov.consent.ui;

import com.proud.egov.consent.model.Organization;
import com.proud.egov.consent.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrganizationController {
    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("api/organizations")
    public Iterable findAll(){return organizationRepository.findAll();}

    @GetMapping("api/organizations/{id}")
    public Organization getOrganizationById(@PathVariable(value="id") String orgID){
        return organizationRepository.findById(orgID)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", orgID));
    }
}
