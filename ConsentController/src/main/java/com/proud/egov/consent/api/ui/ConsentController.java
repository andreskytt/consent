package com.proud.egov.consent.api.ui;

import com.proud.egov.consent.repository.ConsentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ui/consents.json")
public class ConsentController {
    @Autowired
    private ConsentRepository consentRepository;

    @GetMapping
    public Iterable findAll(){
        return consentRepository.findAll();
    }

}
