package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepository extends JpaRepository<Asociacion, Integer> {
}
