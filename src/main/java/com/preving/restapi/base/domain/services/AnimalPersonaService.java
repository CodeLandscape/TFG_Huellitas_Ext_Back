package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.AnimalPersonaDto;
import com.preving.restapi.base.domain.entity.Animal;

import java.util.List;

public interface AnimalPersonaService {
    List<AnimalPersonaDto> findByAnimalId(Animal idAnimal);

}
