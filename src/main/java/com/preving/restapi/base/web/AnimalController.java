package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.dto.AnimalDto;
import com.preving.restapi.base.domain.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody AnimalDto animal) {
        try {
            return new ResponseEntity<>(animalService.add(animal), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> fidndAll(@RequestParam(required = false) String strSearch,
                                     @RequestParam(required = false) List<Long> idTipoAnimal,
                                     @RequestParam(required = false) List<Long> idRaza,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "12") int limit,
                                     @RequestParam(defaultValue = "id") String sort,
                                     @RequestParam(defaultValue = "asc") String order) {
        try {
            return new ResponseEntity<>(animalService.findAll(strSearch, idTipoAnimal, idRaza, page, limit, sort, order), HttpStatus.OK);
        } catch (
                Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(animalService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            animalService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
