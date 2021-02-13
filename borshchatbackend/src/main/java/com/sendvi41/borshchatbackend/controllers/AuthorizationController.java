package com.sendvi41.borshchatbackend.controllers;



import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/consult")
public class AuthorizationController {

    @Autowired
    ConsultantRepository consultantRepository;


    @PostMapping(value = "/authorization", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Consultant authorization(@RequestBody Consultant consultant)
    {
        Consultant cons = consultantRepository.validate(consultant.getName(),consultant.getPassword());
        if(cons.getId()>0) {
            return cons;
        }
        return cons;
    }
//    @GetMapping("/authorization")
//    public String get()
//    {
//        return "d";
//    }





}
