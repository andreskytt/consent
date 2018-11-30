package com.proud.egov.consent.api.open;

import com.proud.egov.consent.model.Service;
import com.proud.egov.consent.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("api/public/services")
    public Iterable findAll(){return serviceRepository.findAll();}

    @GetMapping("api/public/services/{id}")
    public Service getServiceByID(@PathVariable(value = "id") String svcID){
        return serviceRepository.findById(svcID)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", svcID));
    }

}
