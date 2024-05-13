package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String username);
}
