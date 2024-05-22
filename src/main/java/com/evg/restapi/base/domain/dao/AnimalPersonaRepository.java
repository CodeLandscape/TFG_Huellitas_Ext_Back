package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.AnimalPersona;
import com.evg.restapi.base.domain.entity.AnimalPersonaId;
import com.evg.restapi.base.domain.entity.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface AnimalPersonaRepository extends JpaRepository<AnimalPersona, AnimalPersonaId> {
    List<AnimalPersona> findByIdAnimal(Animal idAnimal);
    List<AnimalPersona> findAll();
    List<AnimalPersona> findByIdPersona_Id(Integer idPersona);

    List<AnimalPersona> findByIdAnimal_IdAsociacion(Asociacion idAsociacion);
}
