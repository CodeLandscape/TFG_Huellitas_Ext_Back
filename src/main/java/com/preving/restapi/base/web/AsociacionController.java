package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.services.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/asociacion")
public class AsociacionController {

    @Autowired
    private AsociacionService asociacionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(asociacionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
