package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.PersonaDto;

public interface PersonaService {
    public PersonaDto findById(Integer id);
}
