package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto update(String email, UsuarioDto usuarioDto);

    void desactivarUsuario(Integer id);

    void activarUsuario(Integer id);

    //listar a todos los usuarios
    List<UsuarioDto> findAll();

    UsuarioDto findByCorreo(String correo);

    void desactivarUsuarioEmail(String email);
}
