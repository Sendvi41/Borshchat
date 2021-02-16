package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.services.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/consult")
public class AuthorizationController {

    @Autowired
    ConsultantService consultantService;




    @PostMapping(value = "/authorization", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity authorization(@RequestBody Consultant consultant) {
        if (consultantService.validate(consultant)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping("/authorization")
//    public String get()
//    {
//        return "d";
//    }


}
