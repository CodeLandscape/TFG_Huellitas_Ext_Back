package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.TipoAnimalRepository;
import com.evg.restapi.base.domain.entity.TipoAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAnimalServiceImp implements TipoAnimalService {

    @Autowired
    private TipoAnimalRepository tipoAnimalRepository;

    @Override
    public void addTipoAnimal(String tipo) {
        TipoAnimal nuevoTipoAnimal = new TipoAnimal();
        nuevoTipoAnimal.setNombre(tipo);
        tipoAnimalRepository.save(nuevoTipoAnimal);
    }

    @Override
    public List<TipoAnimal> findAll() {
        return tipoAnimalRepository.findAll();
    }

    @Override
    public void deleteTipoAnimal(Integer id) {
        tipoAnimalRepository.deleteById(id);
    }

    @Override
    public TipoAnimal findById(Integer id) {
        return tipoAnimalRepository.findById(id).get();
    }
}