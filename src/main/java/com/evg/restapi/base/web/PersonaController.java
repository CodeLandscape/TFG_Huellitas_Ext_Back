package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.services.PersonaService;
import com.evg.restapi.base.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "https://tfg-huellitas-ext-front.onrender.com")
@RequestMapping(value = "/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(personaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/sesion")
    public ResponseEntity<?> getPersonaSesion(HttpServletRequest request) {
        try {
            String email = jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(personaService.getPersonaSesion(email), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<?> getByUsuarioId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(personaService.findByUsuarioId(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
