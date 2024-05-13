package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
