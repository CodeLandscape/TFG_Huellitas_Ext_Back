package com.preving.restapi.base.domain.dao;

import com.preving.restapi.base.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
