package com.sendvi41.borshchatbackend.controllers;



import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import com.sendvi41.borshchatbackend.services.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/consult")
public class AuthorizationController {

    @Autowired
    ConsultantService consultantService;



    @PostMapping(value = "/authorization", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Consultant authorization(@RequestBody Consultant consultant)
    {


        return new Consultant();
    }



//    @GetMapping("/authorization")
//    public String get()
//    {
//        return "d";
//    }





}
