package com.proud.egov.consent.client.ui;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.SecretKey;
import java.io.*;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthRequestController {
    private final static String keyFile = "shared.key";
    protected transient final Log logger = LogFactory.getLog(this.getClass());

    @PostMapping("/save")
    String generateRequest(Model model,
                           @RequestParam("dataConsumerID") String dataConsumerID,
                           @RequestParam("userID") String userID,
                           @RequestParam("serviceIDs") String serviceIDs,
                           @RequestParam("purpose") String purpose){
        SecretKey key;

        if ((key = retrieveKey(model)) == null){
            return "index";
        }

        String jwt = Jwts.builder()
                .setIssuer(dataConsumerID)
                .setSubject(userID)
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .claim("serviceIDs", serviceIDs)
                .claim("purpose", purpose)
                .signWith(key)
                .compact();
        model.addAttribute("jwt", jwt);
        return "index";
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
            if(e instanceof FileNotFoundException){
                File absoluteFile = new File(keyFile).getAbsoluteFile();
                logger.warn("Key file " + absoluteFile + " not found, generating a new one");
                key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
                try {
                    ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(new File(keyFile)));
                    oout.writeObject(key);
                    oout.close();
                } catch (IOException ee) {
                    logger.error("Could not create " + absoluteFile + ": " + ee.getMessage());
                    ee.printStackTrace();
                    model.addAttribute("error", ee.getMessage());
                    return null;
                }
            }else {
                e.printStackTrace();
                model.addAttribute("error", e.getMessage());
                return null;
            }
        }
        return key;
    }
}
