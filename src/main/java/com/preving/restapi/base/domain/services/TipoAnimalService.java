package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.entity.TipoAnimal;

import java.util.List;

public interface TipoAnimalService {

    //añadir un nuev tipo de animal
    public void addTipoAnimal(String tipo);

    //listar todos los tipos de animales
    public List<TipoAnimal> findAll();

    //borrar un tipo de animal por ID
    public void deleteTipoAnimal(Integer id);

    public TipoAnimal findById(Integer id);
}
