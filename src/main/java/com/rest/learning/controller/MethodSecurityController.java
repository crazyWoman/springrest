package com.rest.learning.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @PreAuthorize,@PostAuthorize,@PreFilter,@PostFilter (spring security latest version)
 * @Secured (old version) do not support spel
 * @RolesAllowed (jsr 250)
 * ,securedEnabled = true
 *
 */

@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
@RestController
public class MethodSecurityController {

//    List<User> nameLst = List.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    @PreAuthorize("hasAnyRole('lead','admin')") //SPEL
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
       // UserDetails usr = User.withUsername("smitha").roles("programmer").build();
       User user1 =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       //User usr = new User("smitha", "albert", List.of());
        return user1;
    }


    @Secured("ROLE_admin") //old spring security, no spEL
    @GetMapping("/kidsBooks.jkl")
    public String welcomeWorld5(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities()+"adminrole with secured";
    }

    @RolesAllowed("ROLE_admin") //java jsr 250 annotation
    @GetMapping("/kidsBooks.mno")
    public String welcomeWorld6(){
        return "admin/lead role with jsr";
    }

    @GetMapping("/kidsBooks.pqr")
    public List<User>  welcomeWorld7(){
      return   this.getData(
                List.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
              (User)User.withUsername("Danny").roles("employeee").password("hello").build())
        );
    }

    @PostFilter("hasRole('ROLE_employeee')")
    public List<User> getData(List<User> str){
        return str;
    }

}
