package com.rest.learning.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class MethodSecurityController {

    @PreAuthorize("hasAnyRole('lead','admin')")
    @GetMapping("/kidsBooks")
    public String welcomeWorld(){
        return "Elsa and Hannah, Snug Bug";
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/kidsBooks.abc")
    public String welcomeWorld2(){
        return "Elsa and Hannah 2, Snug Bug 2";
    }
}
