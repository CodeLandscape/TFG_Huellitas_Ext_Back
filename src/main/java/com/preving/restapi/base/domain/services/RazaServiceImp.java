package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.RazaRepository;
import com.preving.restapi.base.domain.dao.TipoAnimalRepository;
import com.preving.restapi.base.domain.dto.RazaDto;
import com.preving.restapi.base.domain.entity.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaServiceImp implements RazaService{
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private TipoAnimalRepository tipoAnimalRepository;
    @Override
    public Raza addRaza(RazaDto razaDto ) {
        Raza raza = razaDto.toEntity();
        raza.setIdTipoAnimal(tipoAnimalRepository.findById(razaDto.getIdTipoAnimal()).get());

        return razaRepository.save(raza);
    }

    @Override
    public void deleteRaza(Integer id) {
        razaRepository.deleteById(id);
    }

    @Override
    public void updateRaza(RazaDto razaDto) {
        Raza raza = razaDto.toEntity();
        raza.setIdTipoAnimal(tipoAnimalRepository.findById(razaDto.getIdTipoAnimal()).get());
        razaRepository.save(raza);
    }


    @Override
    public List<Raza> findAll() {
        return razaRepository.findAll();
    }

    @Override
    public List<Raza> findByIdTipoAnimal(Integer idTipoAnimal) {
        return razaRepository.findByIdTipoAnimal(tipoAnimalRepository.findById(idTipoAnimal).get());
    }

    @Override
    public Raza findById(Integer id) {
        return razaRepository.findById(id).get();
    }


}
