package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.dto.AnimalDto;
import com.preving.restapi.base.domain.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
