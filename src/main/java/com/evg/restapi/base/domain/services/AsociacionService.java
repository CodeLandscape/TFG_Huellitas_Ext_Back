package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.AsociacionDto;
import com.evg.restapi.base.domain.entity.Asociacion;

import java.util.List;

public interface AsociacionService {
    public AsociacionDto findById(Integer id);

    public List<AsociacionDto> findAll();

    public void desactivarAsociacion(Integer id);

    public Asociacion findByUsuarioId(Integer id);
}
