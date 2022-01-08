package com.rest.learning.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcomeWorld(){
        return "Welcome world";
    }

    @GetMapping("/employeeName")
    public String getEmployee(){
        return  "smitha";
    }

    @RequestMapping(value ="/welcomeworld",method = RequestMethod.GET)
    public String welcomeToWorld(){
        return "Welcome world";
    }

    @RequestMapping(value ="/lead",method = RequestMethod.GET)
    public String leadName(){
        SecurityContextHolder.clearContext();

        return "Angel Hernandez";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
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
    }
}
