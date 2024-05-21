package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.PersonaDto;
import com.evg.restapi.base.domain.entity.Persona;

public interface PersonaService {
    public PersonaDto findById(Integer id);

    public Persona findByUsuarioId(Integer id);
}
