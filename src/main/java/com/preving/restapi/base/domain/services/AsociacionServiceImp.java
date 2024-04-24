package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AsociacionRepository;
import com.preving.restapi.base.domain.entity.Asociacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsociacionServiceImp implements AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Override
    @Transactional
    public void desactivarAsociacion(Integer id) {
        Asociacion asociacion = asociacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Asociacion no encontrada"));
        asociacion.getIdUsuario().desactivar();
        asociacionRepository.save(asociacion);
    }
}
