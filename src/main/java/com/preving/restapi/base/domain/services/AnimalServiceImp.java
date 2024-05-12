package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AnimalRepository;
import com.preving.restapi.base.domain.dao.AsociacionRepository;
import com.preving.restapi.base.domain.dao.RazaRepository;
import com.preving.restapi.base.domain.dto.AnimalDto;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.entity.Animal;
import com.preving.restapi.base.domain.entity.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImp implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private AsociacionRepository asociacionRepository;

    @Transactional
    public AnimalDto add(AnimalDto animal) {
        AsociacionDto asociacion = new AsociacionDto(asociacionRepository.findById(animal.getAsociacion().getId()).orElse(null));
        animal.setAsociacion(asociacion);
        animal.setActivo(true);
        animal.setFechaAdopcion(null);
        Animal animalEntity = animal.toEntity();
        animalEntity.setIdRaza(razaRepository.findById(animal.getRaza().getId()).orElse(null));
        animalEntity = animalRepository.save(animalEntity);
        return new AnimalDto(animalEntity);
    }

    @Transactional
    @Override
    public Page<AnimalDto> findAll(String strSearch, List<Long> idTipoAnimal, List<Long> IdRaza, int page, int limit, String sort, String order) {

        Specification<Animal> spec = this.animalSpecification(strSearch, idTipoAnimal, IdRaza);

        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(order), sort));
        Page<Animal> animalPage = animalRepository.findAll(spec, pageable);

        List<AnimalDto> animalDtos = new ArrayList<>();
        for (Animal animal : animalPage.getContent()) {
            animalDtos.add(new AnimalDto(animal));
        }

        return new PageImpl<>(animalDtos, pageable, animalPage.getTotalElements());
    }

    @Override
    public void delete(Integer id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        animal.setActivo(false);
        animalRepository.save(animal);
    }

    @Override
    public AnimalDto findById(Integer id) {
        return new AnimalDto(animalRepository.findById(id).orElse(null));
    }

    @Override
    public void update(Integer id, AnimalDto animal) {
        Animal animalEntity = this.animalRepository.findById(id).orElse(null);

        animalEntity.setNombre(animal.getNombre());
        animalEntity.setFechaNac(
                animal.getFechaNac().toInstant()
        );
        animalEntity.setFechaLlegadaAsoc(
                animal.getFechaLlegadaAsoc().toInstant()
        );
        animalEntity.setObservaciones(animal.getObservaciones());
        animalEntity.setIdRaza(razaRepository.findById(animal.getRaza().getId()).orElse(null));

        animalRepository.save(animalEntity);
    }

    private Specification<Animal> animalSpecification(String strSearch, List<Long> idTipoAnimal, List<Long> IdRaza) {
        return (Specification<Animal>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (strSearch != null && !strSearch.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("nombre"), "%" + strSearch + "%")
                ));
            }
            if (idTipoAnimal != null && !idTipoAnimal.isEmpty()) {
                Join<Animal, Raza> razaJoin = root.join("idRaza");
                predicates.add(razaJoin.get("tipoAnimal").in(idTipoAnimal));
            }
            if (IdRaza != null && !IdRaza.isEmpty()) {
                predicates.add(root.get("idRaza").in(IdRaza));
            }
            predicates.add(criteriaBuilder.equal(root.get("activo"), true));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
