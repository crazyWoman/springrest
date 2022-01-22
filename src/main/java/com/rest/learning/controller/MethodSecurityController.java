package com.rest.learning.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@RestController
public class MethodSecurityController {

    @PreAuthorize("hasAnyRole('lead','admin')")
    @GetMapping("/kidsBooks")
    public String welcomeWorld(){
        return "Elsa and Hannah, Snug Bug";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/kidsBooks.abc")
    public String welcomeWorld2(){
        return "Elsa and Hannah 2, Snug Bug 2";
    }

    @PreAuthorize("hasAuthority('manager')")
    @GetMapping("/kidsBooks.def")
    public String welcomeWorld3(){
        return "Elsa and Hannah 3, Snug Bug 3";
    }

    @PostAuthorize("returnObject.username == authentication.name")
    @GetMapping("/kidsBooks.ghi")
    public User welcomeWorld4(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    @Secured("ROLE_admin") //old spring security, no spEL
    @GetMapping("/kidsBooks.jkl")
    public String welcomeWorld5(){
        return "adminrole with secured";
    }

    @RolesAllowed("ROLE_admin") //java jsr 250 annotation
    @GetMapping("/kidsBooks.mno")
    public String welcomeWorld6(){
        return "admin/lead role with jsr";
    }

}
