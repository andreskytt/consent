package com.proud.egov.consent.repository;

import com.proud.egov.consent.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, String> {
    @Override
    List<Service> findAll();
}
