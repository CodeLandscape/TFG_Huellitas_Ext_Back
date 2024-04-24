package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AsociacionRepository;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.entity.Asociacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AsociacionServiceImp implements AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Transactional
    public AsociacionDto findById(Integer id) {
        Asociacion asociacion = asociacionRepository.findById(id).orElse(null);
        if (asociacion != null) {
            return new AsociacionDto(asociacion);
        }
        return null;
    }
}
