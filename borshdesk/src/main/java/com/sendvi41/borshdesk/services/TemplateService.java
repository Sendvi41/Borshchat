package com.sendvi41.borshdesk.services;


import com.sendvi41.borshdesk.dto.Consultant;
import com.sendvi41.borshdesk.dto.Template;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TemplateService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String WS_URI = "http://localhost:8080/template";
    private final Logger logger = Logger.getLogger(TemplateService.class.getName());



    public List<Template> getTemplates(Long id) {
        try {
            return this.restTemplate.exchange(
                    WS_URI + "/gettemplates/"+id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Template>>() {
                    }
            ).getBody();
        } catch (Exception ex) {
            logger.warn(ex);
            return null;
        }
    }

    public Boolean createTemplate(String message, Long consultid){
        Template template = new Template();
        template.setMessage(message);
        template.setConsultant_id(consultid);

        try {
        ResponseEntity<String> response
                = restTemplate.postForEntity(WS_URI + "/createtemplate",
                new HttpEntity<>(template), String.class);


            return response.getStatusCode().equals(HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.warn(ex);
            return false;
        }

    }



}
