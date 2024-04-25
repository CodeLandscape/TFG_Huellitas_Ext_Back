package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(provinciaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
