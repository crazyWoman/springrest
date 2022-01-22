package com.rest.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigLearning extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("smitha")
                .password(passwordEncoder().encode("rohan")).roles("employee1")
                .and()
                .withUser("albert").password(passwordEncoder().encode("miriyam")).roles("lead")
                .and()
                .withUser("rhea").password(passwordEncoder().encode("kerala")).roles("admin", "lead").authorities("manager");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(User.withUsername("Angel").password(passwordEncoder().encode("albert")).roles("lead"))
                .withUser(User.withUsername("Windy").password(passwordEncoder().encode("christine")).roles("admin"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic(). authenticationEntryPoint(authenticationEntryPoint).and()
                .authorizeRequests()
     /*           .antMatchers("/welcome").permitAll()
                .antMatchers("/lead").hasRole("lead")
                .antMatchers("/employeeName").hasRole("admin")*/
                .mvcMatchers("/kidsBooks").permitAll()
                .anyRequest().authenticated().and()

                ;

    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

}
