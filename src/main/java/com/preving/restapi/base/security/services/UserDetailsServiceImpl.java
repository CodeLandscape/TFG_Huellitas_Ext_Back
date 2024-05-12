package com.preving.restapi.base.security.services;

import com.preving.restapi.base.domain.dao.UsuarioRepository;
import com.preving.restapi.base.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getIdRol().getName().name());
        return new User(usuario.getCorreo(), usuario.getPassword(), Collections.singletonList(authority));
    }
}