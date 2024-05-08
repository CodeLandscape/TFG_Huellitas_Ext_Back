package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.services.ImagenAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/imagen-animal")
public class ImagenAnimalController {

    @Autowired
    private ImagenAnimalService imagenAnimalService;

    // Método para buscar imágenes por ID de animal
    @GetMapping(value = "/animal/{id}")
    public ResponseEntity<?> findByAnimalId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(imagenAnimalService.findByAnimalId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para subir una imagen
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file,
                                        @RequestParam(name = "idAnimal") Integer idAnimal) {
        try {
            return new ResponseEntity<>(imagenAnimalService.uploadImage(file, idAnimal), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para eliminar una imagen por su ID
    @DeleteMapping("/imagen/{id}")
    public ResponseEntity<?> deleteImagen(@PathVariable Integer id) {
        try {
            imagenAnimalService.deleteImagen(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
