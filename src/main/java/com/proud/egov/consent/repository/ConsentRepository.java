package com.proud.egov.consent.repository;

import com.proud.egov.consent.model.Consent;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, String> {
    @Override
    <S extends Consent> List<S> findAll(Example<S> example);
}
