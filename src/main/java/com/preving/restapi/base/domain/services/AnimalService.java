package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.AnimalDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimalService {
    public AnimalDto add(AnimalDto animal);

    public Page<AnimalDto> findAll(String strSearch, List<Long> idTipoAnimal, List<Long> IdRaza, int page, int limit, String sort, String order);

    public void delete(Integer id);
}
