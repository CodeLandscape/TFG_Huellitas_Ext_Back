package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.dto.TipoAnimalDto;
import com.preving.restapi.base.domain.services.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/tipoAnimal")
public class TipoAnimalController {

    @Autowired
    private TipoAnimalService tipoAnimalService;

    //listar todos los tipos de animales
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(tipoAnimalService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //añadir u nnuevo tipo de animal por parametro
    @PostMapping(value = "/add")
    public ResponseEntity<?> addTipoAnimal(@RequestBody TipoAnimalDto tipoAnimalDto) {
        try {
            tipoAnimalService.addTipoAnimal(tipoAnimalDto.getNombre());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //borrar un tipo de animal por id
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTipoAnimal(@PathVariable Integer id) {
        try {
            tipoAnimalService.deleteTipoAnimal(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //buscar un tipo de animal por id
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(tipoAnimalService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

