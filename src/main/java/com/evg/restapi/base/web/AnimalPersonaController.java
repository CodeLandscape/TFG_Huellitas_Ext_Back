package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.dto.AnimalPersonaDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.services.AnimalPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animalPersona")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalPersonaController {

    @Autowired
    private AnimalPersonaService animalPersonaService;

    @GetMapping("/{idAnimal}")
    public List<AnimalPersonaDto> getAnimalPersonasByAnimalId(@PathVariable Integer idAnimal) {
        Animal animal = new Animal();
        animal.setId(idAnimal);
        return animalPersonaService.findByAnimalId(animal);
    }

//    @DeleteMapping("/{idPersona}/{idAnimal}")
//    public void deleteAnimalPersona(@PathVariable Integer idPersona, @PathVariable Integer idAnimal) {
//        animalPersonaService.deleteById(idPersona, idAnimal);
//    }

    @DeleteMapping("/deleteSolicitud")
    public void deleteAnimalPersona(@RequestBody AnimalPersonaDto animalPersonaDto) {
        animalPersonaService.deleteById(animalPersonaDto);
    }

    @GetMapping("/all")
    public List<AnimalPersonaDto> getAllAnimalPersonas() {
        return animalPersonaService.findAll();
    }

    @GetMapping("/persona/{idPersona}")
    public List<AnimalPersonaDto> getAnimalPersonasByPersonaId(@PathVariable Integer idPersona) {
        return animalPersonaService.findByIdPersonaId(idPersona);
    }

    @PutMapping("/updateEstado")
    public void updateEstadoAnimalPersona(@RequestBody AnimalPersonaDto animalPersonaDto) {
        animalPersonaService.updateEstado(animalPersonaDto);
    }
}