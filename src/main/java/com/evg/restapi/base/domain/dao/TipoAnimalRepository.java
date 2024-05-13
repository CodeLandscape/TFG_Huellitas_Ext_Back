package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Integer> {
    Optional<TipoAnimal> findById(Integer id);
}
