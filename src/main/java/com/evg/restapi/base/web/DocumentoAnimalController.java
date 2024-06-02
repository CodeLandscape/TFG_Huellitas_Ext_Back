package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.services.DocumentoAnimalService;
import com.evg.restapi.base.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "https://tfg-huellitas-ext-front.onrender.com")
@RequestMapping(value = "/documento-animal")
public class DocumentoAnimalController {

    @Autowired
    private DocumentoAnimalService documentoAnimalService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findByArchivoId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(documentoAnimalService.findByArchivoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/info-id/{id}")
    public ResponseEntity<?> findInfoByArchivoId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(documentoAnimalService.findInfoByArchivoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/info/{id}")
    public ResponseEntity<?> findAllFromAnimal(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(documentoAnimalService.findInfoByAnimalId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_ASOC')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file,
                                        @RequestParam(name = "nombre") String nombre,
                                        @RequestParam(name = "descripcion") String descripcion,
                                        @RequestParam(name = "idAnimal") String idAnimal,
                                        HttpServletRequest request) {
        try {
            return new ResponseEntity<>(documentoAnimalService.uploadFile(file, nombre, descripcion, Integer.valueOf(idAnimal)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_ASOC')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteArchivo(@PathVariable Integer id) {
        try {
            documentoAnimalService.deleteArchivo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_ASOC')")
    @PutMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editArchivo(@RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "nombre") String nombre,
                                         @RequestParam(name = "descripcion") String descripcion,
                                         @RequestParam(name = "file", required = false) MultipartFile file) {
        try {
            documentoAnimalService.editArchivo(id, nombre, descripcion, file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
