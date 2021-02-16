package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.services.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/consult")
public class AuthorizationController {

    @Autowired
    ConsultantService consultantService;




    @PostMapping(value = "/authorization", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response authorization(@RequestBody Consultant consultant) {
        if (consultantService.validate(consultant)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


//    @GetMapping("/authorization")
//    public String get()
//    {
//        return "d";
//    }


}
