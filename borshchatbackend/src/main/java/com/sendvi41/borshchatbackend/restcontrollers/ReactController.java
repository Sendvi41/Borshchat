package com.sendvi41.borshchatbackend.restcontrollers;

import com.sun.enterprise.module.bootstrap.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReactController {

    @RequestMapping(value = "/")
    public String getIndex(HttpServletRequest request) {
        return "index.html";
    }
    @RequestMapping(value = "/tasks")
    public String getTasks(HttpServletRequest request) {
        return "index.html";
    }

    @RequestMapping(value = "/docs")
    public String getDocs(HttpServletRequest request) {
        return "index.html";
    }

//    @RequestMapping(value = {"/task/{\\d*}"})
    @RequestMapping(value = { "/task", "/task{x:[\\w\\-]+}" })
    public String getTask( HttpServletRequest request) {
        return "index.html";
    }
}