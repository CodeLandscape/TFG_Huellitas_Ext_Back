package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.dto.AnimalPersonaDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.services.AnimalPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animalPersona")
public class AnimalPersonaController {

    @Autowired
    private AnimalPersonaService animalPersonaService;

    @GetMapping("/{idAnimal}")
    public List<AnimalPersonaDto> getAnimalPersonasByAnimalId(@PathVariable Integer idAnimal) {
        Animal animal = new Animal();
        animal.setId(idAnimal);
        return animalPersonaService.findByAnimalId(animal);
    }
}