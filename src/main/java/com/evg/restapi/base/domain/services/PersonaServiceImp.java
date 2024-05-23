package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.PersonaRepository;
import com.evg.restapi.base.domain.dao.UsuarioRepository;
import com.evg.restapi.base.domain.dto.PersonaDto;
import com.evg.restapi.base.domain.entity.Persona;
import com.evg.restapi.base.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public PersonaDto findById(Integer id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            return new PersonaDto(persona);
        }
        return null;
    }

    @Transactional
    @Override
    public PersonaDto findByUsuarioId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Persona persona = personaRepository.findPersonaByIdUsuario(usuario);
        System.out.println(persona);
        PersonaDto personaDto = new PersonaDto(persona);
        return personaDto;
//        if (persona != null) {
//            return new PersonaDto(persona);
//        }
//        return null;
    }
}
