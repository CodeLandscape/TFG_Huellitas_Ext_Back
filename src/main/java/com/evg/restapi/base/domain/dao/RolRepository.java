package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Rol;
import com.evg.restapi.base.security.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(Roles nombre);
}
