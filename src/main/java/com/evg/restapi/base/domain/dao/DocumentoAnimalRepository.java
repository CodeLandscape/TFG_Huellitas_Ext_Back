package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Admin;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.DocumentoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoAnimalRepository extends JpaRepository<DocumentoAnimal, Integer> {
    DocumentoAnimal[] findAllByIdAnimal(Animal animal);
}
