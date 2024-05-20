package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Persona findByDni (String dni);

    @Query("SELECT p FROM Persona p WHERE p.idUsuario.correo = :email")
    Persona findByEmailUsuario(String email);
}
