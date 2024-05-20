package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.ArchivosAsociacion;
import com.evg.restapi.base.domain.entity.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivosAsociacionRepository extends JpaRepository<ArchivosAsociacion, Integer> {
    ArchivosAsociacion[] findAllByIdAsociacion(Asociacion asociacion);
}
