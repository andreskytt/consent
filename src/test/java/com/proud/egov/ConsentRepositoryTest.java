package com.proud.egov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;


@RunWith(SpringRunner.class)
public class ConsentRepositoryTest {
    @TestConfiguration
    static class ConsentRepositoryTestConfiguration{
        @Bean
        public ConsentRepository consentRepository(){
            return new ConsentRepository();
        }
    }

    @Autowired
    ConsentRepository consentRepository;

    @Test
    public void testFind(){
        assertNotNull(consentRepository.findConsent("user", "DCID", "SVCID"));
/*
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://jio.com/egov/consent">
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
        assertNull(consentRepository.findConsent("user", "DCID", "NoSuchService"));
/*
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://jio.com/egov/consent">
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
        assertNull(consentRepository.findConsent("NoSuchUser", "NoSuchDataConsumer", "NoSuchService"));
        /*
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://jio.com/egov/consent">
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
