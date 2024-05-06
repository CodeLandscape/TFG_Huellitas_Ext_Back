package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.RazaDto;
import com.preving.restapi.base.domain.entity.Raza;

import java.util.List;

public interface RazaService {
    public Raza addRaza(RazaDto raza);

    public void deleteRaza(Integer id);

    public List<Raza> findByIdTipoAnimal(Integer idTipoAnimal);

    public List<Raza> findAll();

    public Raza findById(Integer id);

    public void updateRaza(RazaDto raza);

}
