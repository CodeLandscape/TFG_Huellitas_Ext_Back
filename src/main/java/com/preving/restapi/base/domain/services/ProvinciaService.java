package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.dto.ProvinciaDto;

import java.util.List;

public interface ProvinciaService {
    public List<ProvinciaDto> findAll();
}