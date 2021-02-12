package com.sendvi41.borshchatbackend.controllers;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorizationController {

    @PostMapping("/autorization")
    public String get()
    {

        return "aaa";
    }



}
