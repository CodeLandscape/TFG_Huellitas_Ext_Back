package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.dto.UsuarioDto;
import com.evg.restapi.base.domain.services.UsuarioService;
import com.evg.restapi.base.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PutMapping(value = "update")
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody UsuarioDto usuario) {
        try {
            String email = jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(usuarioService.update(email, usuario), HttpStatus.OK);
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

    @PutMapping("baja-sesion")
    public ResponseEntity<Void> desactivarUsuarioSesion(HttpServletRequest request) {
        String email = jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
        usuarioService.desactivarUsuarioEmail(email);
        return ResponseEntity.noContent().build();
    }

    //listar a todos los usuarios
    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(HttpServletRequest request) {
        try{
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
                if (jwtUtil.validateToken(token)) {
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Token is not valid", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
