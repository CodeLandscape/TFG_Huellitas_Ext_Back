package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
