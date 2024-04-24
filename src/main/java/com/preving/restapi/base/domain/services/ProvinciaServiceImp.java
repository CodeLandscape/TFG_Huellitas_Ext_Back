package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.ProvinciaRepository;
import com.preving.restapi.base.domain.dto.ProvinciaDto;
import com.preving.restapi.base.domain.entity.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImp implements ProvinciaService{

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Override
    public List<ProvinciaDto> findAll() {
        List<Provincia> provincias = provinciaRepository.findAll();
        return provincias.stream().map(ProvinciaDto::new).toList();
    }
}
