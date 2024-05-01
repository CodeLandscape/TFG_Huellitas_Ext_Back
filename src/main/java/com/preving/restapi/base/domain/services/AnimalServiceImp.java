package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AnimalRepository;
import com.preving.restapi.base.domain.dao.AsociacionRepository;
import com.preving.restapi.base.domain.dao.RazaRepository;
import com.preving.restapi.base.domain.dto.AnimalDto;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.dto.RazaDto;
import com.preving.restapi.base.domain.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class AnimalServiceImp implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private AsociacionRepository asociacionRepository;

    @Transactional
    public AnimalDto add(AnimalDto animal) {
        AsociacionDto asociacion = new AsociacionDto(asociacionRepository.findById(animal.getAsociacion().getId()).orElse(null));
        animal.setAsociacion(asociacion);
        animal.setActivo(true);
        animal.setFechaAdopcion(null);
        Animal animalEntity = animal.toEntity();
        animalEntity.setIdRaza(razaRepository.findById(animal.getRaza().getId()).orElse(null));
        animalEntity = animalRepository.save(animalEntity);
        return new AnimalDto(animalEntity);
    }
}
