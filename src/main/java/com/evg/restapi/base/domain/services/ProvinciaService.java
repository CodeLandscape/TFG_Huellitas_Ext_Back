package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.ProvinciaDto;

import java.util.List;

public interface ProvinciaService {
    public List<ProvinciaDto> findAll();
}