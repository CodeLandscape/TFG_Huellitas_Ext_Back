package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.dto.UsuarioDto;
import com.preving.restapi.base.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioDto usuario) {
        try {
            return new ResponseEntity<>(usuarioService.update(id, usuario), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("baja/{id}")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Integer id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("alta/{id}")
    public ResponseEntity<Void> activarUsuario(@PathVariable Integer id) {
        usuarioService.activarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
