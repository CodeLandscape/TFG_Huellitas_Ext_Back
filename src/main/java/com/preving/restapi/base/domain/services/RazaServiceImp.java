package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.RazaRepository;
import com.preving.restapi.base.domain.dao.TipoAnimalRepository;
import com.preving.restapi.base.domain.dto.RazaDto;
import com.preving.restapi.base.domain.entity.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<RazaDto> findAll() {
        List<Raza> razas = razaRepository.findAll();
        List<RazaDto> razaDtos = new ArrayList<>();

        for (Raza raza : razas) {
            RazaDto razaDto = new RazaDto();
            razaDto.setId(raza.getId());
            razaDto.setNombre(raza.getNombre());
            razaDto.setIdTipoAnimal(raza.getIdTipoAnimal().getId());
            razaDtos.add(razaDto);
        }

        return razaDtos;
    }

    @Override
    public List<RazaDto> findByIdTipoAnimal(Integer idTipoAnimal) {
        List<Raza> razas = razaRepository.findByIdTipoAnimal(tipoAnimalRepository.findById(idTipoAnimal).get());
        List<RazaDto> razaDtos = new ArrayList<>();

        for (Raza raza : razas) {
            RazaDto razaDto = new RazaDto();
            razaDto.setId(raza.getId());
            razaDto.setNombre(raza.getNombre());
            razaDto.setIdTipoAnimal(raza.getIdTipoAnimal().getId());
            razaDtos.add(razaDto);
        }

        return razaDtos;
    }

    @Override
    public RazaDto findById(Integer id) {
        Raza raza = razaRepository.findById(id).get();
        RazaDto razaDto = new RazaDto();
        razaDto.setId(raza.getId());
        razaDto.setNombre(raza.getNombre());
        razaDto.setIdTipoAnimal(raza.getIdTipoAnimal().getId());

        return razaDto;
    }


}
