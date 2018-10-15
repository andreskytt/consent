package com.proud.egov.consent.repository;

import com.proud.egov.consent.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

    @Override
    List<Organization> findAll();
}
