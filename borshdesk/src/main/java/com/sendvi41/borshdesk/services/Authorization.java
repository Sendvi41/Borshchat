package com.sendvi41.borshdesk.services;



import com.sendvi41.borshdesk.entities.Consultant;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Service
public class Authorization {
    private static final String WS_URI = "http://localhost:8080/api/consult/authorization";



    private static Boolean authorizate(Consultant consultant) {



        return false;
    }




    public static boolean checkWS() {
        Boolean stateOfWS = false;
        try {
            URL siteURL = new URL(getBaseUri().toString());
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();
            int code = connection.getResponseCode();
            if(code == 200) stateOfWS = true;
        } catch(Exception e) {
            throw new RuntimeException();
        }
        return stateOfWS;
    }

    private static URI getBaseUri() {
        return UriBuilder.fromUri(WS_URI).build();
    }

}
