package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.*;
import com.evg.restapi.base.domain.dto.RazaDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.ImagenAnimal;
import com.evg.restapi.base.domain.entity.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaServiceImp implements RazaService{
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private TipoAnimalRepository tipoAnimalRepository;
    @Autowired
    private AnimalPersonaRepository animalPersonaRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ImagenAnimalRepository imagenAnimalRepository;
    @Autowired
    private DocumentoAnimalRepository documentoAnimalRepository;

    @Override
    public Raza addRaza(RazaDto razaDto ) {
        Raza raza = razaDto.toEntity();
        raza.setTipoAnimal(tipoAnimalRepository.findById(razaDto.getIdTipoAnimal()).get());

        return razaRepository.save(raza);
    }

    @Override
    public void deleteRaza(Integer id) {
        List<Animal> animals = animalRepository.findByIdRaza_Id(id);
        if (animals.isEmpty()){
            razaRepository.deleteById(id);
            return;
        }
        boolean animalesDesactivados = animals.stream().allMatch(animal -> animal.getActivo().equals(false));
        if (!animalesDesactivados) {
            throw new RuntimeException("No se pueden eliminar razas con animales activos");
        } else {
            animals.forEach(animal -> {
                ImagenAnimal imagenes = imagenAnimalRepository.findByIdAnimal_Id(animal.getId());
                imagenAnimalRepository.delete(imagenes);
                animalPersonaRepository.deleteByIdAnimal_Id(animal.getId());
                documentoAnimalRepository.deleteByIdAnimal(animal);
                animalRepository.delete(animal);
            });
            razaRepository.deleteById(id);
        }

    }

    @Override
    public void updateRaza(RazaDto razaDto) {
        Raza raza = razaDto.toEntity();
        raza.setTipoAnimal(tipoAnimalRepository.findById(razaDto.getIdTipoAnimal()).get());
        razaRepository.save(raza);
    }


    @Override
    public List<Raza> findAll() {
        return razaRepository.findAll();
    }

    @Override
    public List<Raza> findByIdTipoAnimal(Integer idTipoAnimal) {
        return razaRepository.findByTipoAnimal(tipoAnimalRepository.findById(idTipoAnimal).get());
    }

    @Override
    public Raza findById(Integer id) {
        return razaRepository.findById(id).get();
    }


}