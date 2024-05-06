package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.dto.PersonaDto;
import com.preving.restapi.base.domain.dto.RazaDto;
import com.preving.restapi.base.domain.services.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/raza")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RazaController {

    @Autowired
    private RazaService razaService;

    @PostMapping("/add")
    public ResponseEntity<?> addRaza(@RequestBody RazaDto raza) {
        try{
            return new ResponseEntity<>(razaService.addRaza(raza), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRaza(@PathVariable Integer id) {
        try{
            razaService.deleteRaza(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRaza(@RequestBody RazaDto raza) {
        try{
            razaService.updateRaza(raza);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(razaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByIdTipoAnimal/{idTipoAnimal}")
    public ResponseEntity<?> findByIdTipoAnimal(@PathVariable Integer idTipoAnimal) {
        try{
            return new ResponseEntity<>(razaService.findByIdTipoAnimal(idTipoAnimal), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(razaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
