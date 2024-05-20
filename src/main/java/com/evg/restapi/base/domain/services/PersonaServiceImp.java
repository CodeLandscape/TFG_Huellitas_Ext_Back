package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.PersonaRepository;
import com.evg.restapi.base.domain.dto.PersonaDto;
import com.evg.restapi.base.domain.entity.Persona;
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
            return new PersonaDto(persona, persona.getIdUsuario());
        }
        return null;
    }

    @Override
    @Transactional
    public PersonaDto getPersonaSesion(String email) {
        Persona persona = personaRepository.findByEmailUsuario(email);
        if (persona != null) {
            return new PersonaDto(persona, persona.getIdUsuario());
        }
        return null;
    }
}
