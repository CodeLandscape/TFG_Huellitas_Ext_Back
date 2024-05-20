package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.AnimalPersonaDto;
import com.evg.restapi.base.domain.entity.Animal;

import java.util.List;

public interface AnimalPersonaService {
    List<AnimalPersonaDto> findByAnimalId(Animal idAnimal);
    void deleteById(AnimalPersonaDto animalPersonaDto);

//    void deleteById(AnimalPersonaDto animalPersonaDto);

    List <AnimalPersonaDto> findAll();
    List <AnimalPersonaDto> findByIdPersonaId(Integer idPersona);

    AnimalPersonaDto add(AnimalPersonaDto animalPersonaDto);
}
