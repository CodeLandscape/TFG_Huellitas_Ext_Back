package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Page<Animal> findAll(Specification<Animal> specification, Pageable pageable);

}
