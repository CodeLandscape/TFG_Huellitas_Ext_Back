package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}