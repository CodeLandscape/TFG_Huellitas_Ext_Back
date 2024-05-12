package com.preving.restapi.base.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) {
        try {
            http
                    .authorizeRequests()
                    .antMatchers("/auth/**").permitAll() // Los demás endpoints de auth requieren autenticación
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/**").hasRole("USER")
//                    .antMatchers("/asoc/**").hasRole("ASOC")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().disable();
        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción como prefieras
        }
    }
}