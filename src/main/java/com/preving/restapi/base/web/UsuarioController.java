package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bajaUsuario")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
