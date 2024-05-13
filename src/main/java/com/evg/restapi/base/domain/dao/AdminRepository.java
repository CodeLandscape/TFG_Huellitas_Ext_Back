package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
