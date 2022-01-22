package com.rest.learning.controller;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcomeWorld(){
        return SecurityContextHolder.getContext().getAuthentication().getName()+"Welcome world";
    }

    @GetMapping("/employeeName")
    public String getEmployee(){
        return  "smitha";
    }

    @RequestMapping(value ="/welcomeworld",method = RequestMethod.GET)
    public String welcomeToWorld(){

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal()+"Welcome world";
    }

    @RequestMapping(value ="/lead",method = RequestMethod.GET)
    public String leadName(){
       // SecurityContextHolder.clearContext();
        return SecurityContextHolder.getContext().getAuthentication().getName()+"Angel Hernandez";
    }

   /* @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "logout";
    }*/
}
