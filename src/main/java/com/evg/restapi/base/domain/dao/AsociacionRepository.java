package com.evg.restapi.base.domain.dao;

import com.evg.restapi.base.domain.entity.Asociacion;
import com.evg.restapi.base.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsociacionRepository extends JpaRepository<Asociacion, Integer> {
    Asociacion findByCif (String cif);

    Asociacion findByIdUsuario(Usuario usuario);

    @Query("SELECT a FROM Asociacion a WHERE a.idUsuario.correo = :email")
    Asociacion findByEmailUsuario(String email);

    Asociacion findAsociacionByIdUsuario(Usuario usuario);
}
