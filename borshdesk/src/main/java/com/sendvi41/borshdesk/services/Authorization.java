package com.sendvi41.borshdesk.services;


import com.sendvi41.borshdesk.dto.Consultant;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.UriBuilder;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Service
public class Authorization implements AuthorizationInterface {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String WS_URI = "http://localhost:8080/consult";
    private final Logger logger = Logger.getLogger(Authorization.class.getName());

    @Override
    public Boolean checkLoginAndPassword(Consultant consultant) {
        try {
            ResponseEntity<String> response
                    = restTemplate.postForEntity(WS_URI + "/authorization", new HttpEntity<>(consultant), String.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        } catch (Exception ex) {
            logger.warn(ex);
            return false;
        }

    }

    @Override
    public Consultant getConsultant(Consultant consultant) {
        try {
            return this.restTemplate.exchange(
                    WS_URI + "/getconsultant",
                    HttpMethod.POST,
                    new HttpEntity<>(consultant),
                    new ParameterizedTypeReference<Consultant>() {
                    }
            ).getBody();
        } catch (Exception ex) {
            logger.warn(ex);
            return null;
        }
    }


//this.restTemplate.exchange(
//    WS_URI + "/authorization",
//    HttpMethod.POST,
//            new HttpEntity<>(consultant),
//            new ParameterizedTypeReference<Response>(){
//    }
//        ).getBody();

//    public static boolean checkWS() {
//        Boolean stateOfWS = false;
//        try {
//            URL siteURL = new URL(getBaseUri().toString());
//            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.connect();
//            int code = connection.getResponseCode();
//            if (code == 200) stateOfWS = true;
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//        return stateOfWS;
//    }
//
//    private static URI getBaseUri() {
//        return UriBuilder.fromUri(WS_URI).build();
//    }

}
