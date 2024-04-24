package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>{
    Optional<Provincia> findById (Integer id);
}
