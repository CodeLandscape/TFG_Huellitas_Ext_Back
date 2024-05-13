package com.evg.restapi.base.domain.dto;

import com.evg.restapi.base.security.Roles;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDTO {
    private String token;
    private String bearer = "Bearer";
    private String correo;

    private Integer id;

    private String role;


    private Collection<? extends GrantedAuthority> authorities;

        public JwtDTO(String token, String correo, Integer id, String role) {
        this.token = token;
        this.correo = correo;
        this.id = id;
        this.role = role;
    }

//    public JwtDTO(String token, String correo, Long id, Collection<? extends GrantedAuthority> authorities, Roles role) {
//        this.token = token;
//        this.correo = correo;
//        this.id = id;
//        this.authorities = authorities;
//        this.role = role;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getRole() {
        return role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
