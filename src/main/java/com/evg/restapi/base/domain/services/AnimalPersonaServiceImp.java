package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.AnimalPersonaRepository;
import com.evg.restapi.base.domain.dto.AnimalPersonaDto;
import com.evg.restapi.base.domain.dto.AnimalDto;
import com.evg.restapi.base.domain.dto.PersonaDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.AnimalPersona;
import com.evg.restapi.base.domain.entity.AnimalPersonaId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalPersonaServiceImp implements AnimalPersonaService {

    @Autowired
    private AnimalPersonaRepository animalPersonaRepository;

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