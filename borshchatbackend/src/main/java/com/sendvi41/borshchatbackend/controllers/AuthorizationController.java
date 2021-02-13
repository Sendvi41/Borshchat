package com.sendvi41.borshchatbackend.controllers;



import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/consult")
public class AuthorizationController {

    @Autowired
    ConsultantRepository consultantRepository;


    @PostMapping(value = "/authorization", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response authorization(Consultant consultant)
    {
        Consultant cons = consultantRepository.validate(consultant.getName(),consultant.getPassword());
        if(cons.getId()>0) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
//    @GetMapping("/authorization")
//    public String get()
//    {
//        return "d";
//    }





}
