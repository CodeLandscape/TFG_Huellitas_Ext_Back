package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.AnimalPersonaRepository;
import com.evg.restapi.base.domain.dao.AnimalRepository;
import com.evg.restapi.base.domain.dao.AsociacionRepository;
import com.evg.restapi.base.domain.dao.PersonaRepository;
import com.evg.restapi.base.domain.dto.AnimalPersonaDto;
import com.evg.restapi.base.domain.dto.AnimalDto;
import com.evg.restapi.base.domain.dto.PersonaDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.AnimalPersona;
import com.evg.restapi.base.domain.entity.AnimalPersonaId;
import com.evg.restapi.base.domain.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalPersonaServiceImp implements AnimalPersonaService {

    @Autowired
    private AnimalPersonaRepository animalPersonaRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Override
    @Transactional
    public List<AnimalPersonaDto> findByAnimalId(Animal idAnimal) {
        return animalPersonaRepository.findByIdAnimal(idAnimal)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(AnimalPersonaDto animalPersonaDto) {
        AnimalPersonaId id = new AnimalPersonaId(animalPersonaDto.getIdPersona().getId(), animalPersonaDto.getIdAnimal().getId());
        animalPersonaRepository.deleteById(id);
    }

//    @Override
//    public void deleteById(AnimalPersonaDto animalPersonaDto) {
//        AnimalPersonaId id = new AnimalPersonaId(animalPersonaDto.getIdPersona().getId(), animalPersonaDto.getIdAnimal().getId());
//        animalPersonaRepository.deleteById(id);
//    }

    @Override
    @Transactional
    public List<AnimalPersonaDto> findAll() {
        return animalPersonaRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AnimalPersonaDto> findByIdPersonaId(Integer idPersona) {
        return animalPersonaRepository.findByIdPersona_Id(idPersona)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AnimalPersonaDto add(AnimalPersonaDto animalPersonaDto) {
        AnimalPersona animalPersona = new AnimalPersona();

        // Fetch the Animal and Persona entities from the database
        Animal animal = animalRepository.findById(animalPersonaDto.getIdAnimal().getId())
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        Persona persona = personaRepository.findById(animalPersonaDto.getIdPersona().getId())
                .orElseThrow(() -> new RuntimeException("Persona not found"));

        // Create a new instance of AnimalPersonaId and set Animal and Persona
        AnimalPersonaId id = new AnimalPersonaId();
        id.setAnimal(animal);
        id.setPersona(persona);

        // Set the id in AnimalPersona
        animalPersona.setId(id);
        animalPersona.setIdPersona(persona);
        animalPersona.setIdAnimal(animal);

        animalPersona.setFecha(Instant.now());
        animalPersona.setEstado(false);
        animalPersonaRepository.save(animalPersona);
        return convertToDto(animalPersona);
    }

    @Override
    @Transactional
    public List<AnimalPersonaDto> findByAsociacionId(Integer idAsociacion) {
        return animalPersonaRepository.findByIdAnimal_IdAsociacion(this.asociacionRepository.findById(idAsociacion).get() )
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AnimalPersonaDto convertToDto(AnimalPersona animalPersona) {
        AnimalPersonaDto animalPersonaDto = new AnimalPersonaDto();
        animalPersonaDto.setId(animalPersona.getId());
        animalPersonaDto.setFecha(animalPersona.getFecha());
        animalPersonaDto.setEstado(animalPersona.getEstado());

        AnimalDto animalDto = new AnimalDto(animalPersona.getIdAnimal());
        animalPersonaDto.setIdAnimal(animalDto);

        PersonaDto personaDto = new PersonaDto();
        personaDto.setId(animalPersona.getIdPersona().getId());
        personaDto.setNombre(animalPersona.getIdPersona().getNombre());
        personaDto.setCorreo(animalPersona.getIdPersona().getIdUsuario().getCorreo());
        personaDto.setTlf(animalPersona.getIdPersona().getIdUsuario().getTlf());
        animalPersonaDto.setIdPersona(personaDto);

        return animalPersonaDto;
    }
}
