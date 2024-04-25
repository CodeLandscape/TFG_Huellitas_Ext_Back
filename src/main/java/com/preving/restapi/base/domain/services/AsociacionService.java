package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.AsociacionDto;

import java.util.List;

public interface AsociacionService {
    public AsociacionDto findById(Integer id);

    public List<AsociacionDto> findAll();

    public void desactivarAsociacion(Integer id);
}