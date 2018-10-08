package com.proud.egov.consent;

import com.proud.egov.consent.service.ConsentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;


@RunWith(SpringRunner.class)
public class ConsentServiceTest {
    @TestConfiguration
    static class ConsentRepositoryTestConfiguration{
        @Bean
        public ConsentServiceImpl consentRepository(){
            return new ConsentServiceImpl();
        }
    }

    @Autowired
    ConsentServiceImpl consentRepository;

    @Test
    public void testFind(){
//        assertNotNull(consentRepository.getConsent("user", "DCID", "SVCID"));
/*
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://proud.com/egov/consent">
   <soapenv:Header/>
   <soapenv:Body>
      <con:findConsentRequest>
         <userID>user</userID>
         <DCID>DCID</DCID>
         <serviceID>SVCID</serviceID>
      </con:findConsentRequest>
   </soapenv:Body>
</soapenv:Envelope>
*/
//        assertNull(consentRepository.getConsent("user", "DCID", "NoSuchService"));
/*
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://proud.com/egov/consent">
   <soapenv:Header/>
   <soapenv:Body>
      <con:findConsentRequest>
         <userID>user</userID>
         <DCID>DCID</DCID>
         <serviceID>NoSuchService</serviceID>
      </con:findConsentRequest>
   </soapenv:Body>
</soapenv:Envelope>
 */
//        assertNull(consentRepository.getConsent("NoSuchUser", "NoSuchDataConsumer", "NoSuchService"));
        /*
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://proud.com/egov/consent">
   <soapenv:Header/>
   <soapenv:Body>
      <con:findConsentRequest>
         <userID>NoSuchUser</userID>
         <DCID>NoSuchDataConsumer</DCID>
         <serviceID>NoSuchService</serviceID>
      </con:findConsentRequest>
   </soapenv:Body>
</soapenv:Envelope>
         */
    }
}
