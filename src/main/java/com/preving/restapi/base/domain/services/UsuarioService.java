package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.UsuarioDto;

public interface UsuarioService {
    public UsuarioDto update(Integer id, UsuarioDto usuarioDto);

    public void desactivarUsuario(Integer id);

    public void activarUsuario(Integer id);
}
