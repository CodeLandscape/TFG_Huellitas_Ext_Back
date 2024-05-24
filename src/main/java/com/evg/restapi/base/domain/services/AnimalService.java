package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.AnimalDto;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

public interface AnimalService {
    public AnimalDto add(AnimalDto animal, String email);

    public Page<AnimalDto> findAll(Boolean filtrarPorAsociacion, String email, String strSearch, List<Long> idTipoAnimal, List<Long> IdRaza, int page, int limit, String sort, String order);

    public void delete(Integer id);

    public AnimalDto findById(Integer id);

    void update(Integer id, AnimalDto animal);

    List<AnimalDto> findByAsociacionId(Integer idAsociacion);
}
