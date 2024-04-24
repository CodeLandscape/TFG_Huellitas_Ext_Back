package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.UsuarioRepository;
import com.preving.restapi.base.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void desactivarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.desactivar();
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void activarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.activar();
        usuarioRepository.save(usuario);
    }
}