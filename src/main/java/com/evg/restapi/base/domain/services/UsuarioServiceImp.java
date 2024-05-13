package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.UsuarioRepository;
import com.evg.restapi.base.domain.dto.UsuarioDto;
import com.evg.restapi.base.domain.dao.ProvinciaRepository;
import com.evg.restapi.base.domain.entity.Provincia;
import com.evg.restapi.base.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Transactional
    public UsuarioDto update(Integer id, UsuarioDto usuarioDto) {
        this.validarUsuario(usuarioDto);
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        usuario.setTlf(usuarioDto.getTlf());
        usuario.setDireccion(usuarioDto.getDireccion());
        usuario.setPoblacion(usuarioDto.getPoblacion());
        Provincia provincia = provinciaRepository.findById(usuarioDto.getProvincia().getId()).orElse(null);
        usuario.setIdProvincia(provincia);

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    private void validarUsuario(UsuarioDto usuarioDto) {
        if (usuarioDto.getTlf() == null || usuarioDto.getTlf().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (usuarioDto.getDireccion() == null || usuarioDto.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (usuarioDto.getPoblacion() == null || usuarioDto.getPoblacion().isEmpty()) {
            throw new IllegalArgumentException("La población es obligatoria");
        }
        if (usuarioDto.getProvincia() == null || usuarioDto.getProvincia().getId() == null) {
            throw new IllegalArgumentException("La provincia es obligatoria");
        }
        if(provinciaRepository.findById(usuarioDto.getProvincia().getId()).orElse(null) == null){
            throw new IllegalArgumentException("La provincia no existe");
        }
    }

    @Override
    @Transactional
    public void desactivarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.desactivar();
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void activarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.activar();
        usuarioRepository.save(usuario);
    }

    //listar a todos los usuarios
    @Override
    @Transactional
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
