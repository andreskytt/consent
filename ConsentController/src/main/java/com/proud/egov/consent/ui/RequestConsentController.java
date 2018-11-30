package com.proud.egov.consent.ui;

import com.proud.egov.consent.repository.ServiceRepository;
import io.jsonwebtoken.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.SecretKey;
import java.io.*;

@Controller
public class RequestConsentController {
    @Value("${com.proud.egov.consent.keyFile}")
    private String keyFile;
    private transient final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/getConsent")
    public String getConsent(Model model, @RequestParam("consentRequest") String consentRequest){
        Jws<Claims> jws;
        SecretKey key = retrieveKey(model);
        if (key == null){
            logger.error("Could not read key from " + new File(keyFile).getAbsoluteFile());
        }
        else {
            try {
                jws = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(consentRequest);
                extractClaims(model, jws);
            } catch (JwtException e) {
                logger.error("Invalid JWS received:" + consentRequest);
                e.printStackTrace();
                model.addAttribute("error", e.getMessage());
            }
        }
        return "consent";
    }

    private void extractClaims(Model model, Jws<Claims> jws) {
        String svcIDs = (String)(jws.getBody().get("serviceIDs"));

        model.addAttribute("subject", jws.getBody().getSubject());
        model.addAttribute("issuer", jws.getBody().getIssuer());
        model.addAttribute("issuedAt", jws.getBody().getIssuedAt());
        model.addAttribute("ID", jws.getBody().getId());
        model.addAttribute("svcIDs", jws.getBody().get("serviceIDs"));
        model.addAttribute("purpose", jws.getBody().get("purpose"));
    }

    private SecretKey retrieveKey(Model model){
        SecretKey key;

        try {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File(keyFile)));
            try {
                key = (SecretKey)oin.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                model.addAttribute("error", e.getMessage());
                return null;
            }
            oin.close();
        } catch (IOException e){
                e.printStackTrace();
                model.addAttribute("error", e.getMessage());
                return null;
        }
        return key;
    }

}
