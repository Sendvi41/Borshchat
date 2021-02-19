package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    TemplateService templateService;

    @GetMapping(value = "/gettemplates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Template>> getConsultant(@PathVariable(name = "id") long id) {
        try{
            List<Template> templates = templateService.getTemplatesbyConsultId(id);
            return new ResponseEntity<List<Template>>(templates, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<List<Template>>(HttpStatus.NOT_FOUND);
        }
    }

}
