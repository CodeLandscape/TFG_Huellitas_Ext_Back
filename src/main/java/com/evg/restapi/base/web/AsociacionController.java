package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.services.AsociacionService;
import com.evg.restapi.base.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/asociacion")
public class AsociacionController {

    @Autowired
    private AsociacionService asociacionService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(asociacionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/sesion")
    public ResponseEntity<?> getAsociacionSesion(HttpServletRequest request) {
        try {
            String email = jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(asociacionService.getAsociacionSesion(email), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(asociacionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public AsociacionController(AsociacionService asociacionService) {
        this.asociacionService = asociacionService;
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<Void> desactivarAsociacion(@PathVariable Integer id) {
        asociacionService.desactivarAsociacion(id);
        return ResponseEntity.noContent().build();
    }

}
