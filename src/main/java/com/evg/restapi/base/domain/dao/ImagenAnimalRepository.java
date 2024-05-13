package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImagenAnimalRepository extends JpaRepository<ImagenAnimal, Integer> {
    List<ImagenAnimal> findByIdAnimal(Animal animal);
    Optional<ImagenAnimal> findById(Integer id);
    void deleteById(Long id);

}
