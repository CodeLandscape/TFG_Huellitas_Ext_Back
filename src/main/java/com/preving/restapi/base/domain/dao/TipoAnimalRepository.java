package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Integer> {
}
