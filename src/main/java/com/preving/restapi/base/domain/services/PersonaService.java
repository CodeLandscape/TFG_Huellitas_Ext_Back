package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.PersonaDto;

public interface PersonaService {
    public PersonaDto findById(Integer id);
}
