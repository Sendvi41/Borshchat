package com.sendvi41.borshchatbackend.restcontrollers;

import com.sun.enterprise.module.bootstrap.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReactController {

    @RequestMapping(value = { "/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}" })
    public String getIndex(HttpServletRequest request) {
        return "index.html";
    }

}