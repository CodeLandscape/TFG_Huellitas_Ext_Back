package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Animal;
import com.preving.restapi.base.domain.entity.AnimalPersona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalPersonaRepository extends JpaRepository<AnimalPersona, Integer> {
    List<AnimalPersona> findByIdAnimal(Animal idAnimal);
}