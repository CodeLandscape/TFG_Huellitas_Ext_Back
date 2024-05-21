package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(personaService.findById(id), HttpStatus.OK);
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
