package com.sendvi41.borshchatbackend.controllers;



import com.sendvi41.borshchatbackend.entities.Consultant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthorizationController {

    @PostMapping("/authorization")
    public String authorization()
    {

        return "d";
    }



}
