package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.services.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/asociacion")
public class AsociacionController {

    @Autowired
    private AsociacionService asociacionService;

    public AsociacionController(AsociacionService asociacionService) {
        this.asociacionService = asociacionService;
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<Void> desactivarAsociacion(@PathVariable Integer id) {
        asociacionService.desactivarAsociacion(id);
        return ResponseEntity.noContent().build();
    }

}