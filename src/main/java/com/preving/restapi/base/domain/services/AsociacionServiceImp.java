package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AsociacionRepository;
import com.preving.restapi.base.domain.dao.UsuarioRepository;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.dto.UsuarioDto;
import com.preving.restapi.base.domain.entity.Asociacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsociacionServiceImp implements AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public AsociacionDto findById(Integer id) {
        Asociacion asociacion = asociacionRepository.findById(id).orElse(null);
        if (asociacion != null) {
            return new AsociacionDto(asociacion);
        }
        return null;
    }

    @Override
    @Transactional
    public List<AsociacionDto> findAll() {
        List <Asociacion> asociaciones = asociacionRepository.findAll();
        List<AsociacionDto> asociacionDtos = new ArrayList<>();
        for (Asociacion asociacion : asociaciones) {
            AsociacionDto asociacionDto = new AsociacionDto(asociacion);
            asociacionDto.setUsuario(new UsuarioDto(usuarioRepository.findById(asociacion.getIdUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"))));
            asociacionDtos.add(asociacionDto);
        }
        return asociacionDtos;
    }

    @Override
    @Transactional
    public void desactivarAsociacion(Integer id) {
        Asociacion asociacion = asociacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Asociacion no encontrada"));
        asociacion.getIdUsuario().desactivar();
        asociacionRepository.save(asociacion);
    }
}