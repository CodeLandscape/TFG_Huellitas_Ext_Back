package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.PersonaRepository;
import com.preving.restapi.base.domain.dto.PersonaDto;
import com.preving.restapi.base.domain.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    @Override
    public PersonaDto findById(Integer id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            return new PersonaDto(persona);
        }
        return null;
    }
}
