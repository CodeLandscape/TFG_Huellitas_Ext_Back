package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Persona;
import com.evg.restapi.base.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Persona findByDni (String dni);

    Persona findPersonaByIdUsuario (Usuario id);
}
