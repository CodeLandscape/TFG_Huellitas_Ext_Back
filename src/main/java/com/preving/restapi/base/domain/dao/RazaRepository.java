package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Raza;
import com.preving.restapi.base.domain.entity.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RazaRepository extends JpaRepository<Raza, Integer> {
    List<Raza> findByIdTipoAnimal(TipoAnimal idTipoAnimal);

    List<Raza> findAll();

    Optional<Raza> findById(Integer id);
}
