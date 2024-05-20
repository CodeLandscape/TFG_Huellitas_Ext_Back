package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.services.ArchivoAsociacionService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/archivo-asociacion")
public class ArchivoAsociacionController {

    @Autowired
    private ArchivoAsociacionService archivoAsociacionService;
    @Autowired
    private JwtUtil jwtUtil;

    // Método para buscar imágenes por ID de animal
    @PreAuthorize("hasRole('ROLE_ASOC')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findByArchivoId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(archivoAsociacionService.findByArchivoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_ASOC')")
    @GetMapping(value = "/info")
    public ResponseEntity<?> findAllFromAsoc(HttpServletRequest request) {
        try {
            String emailAsoc = this.jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(archivoAsociacionService.findInfoByAsociacionEmail(emailAsoc), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para subir una imagen
    @PreAuthorize("hasRole('ROLE_ASOC')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file,
                                        @RequestParam(name = "nombre") String nombre,
                                        @RequestParam(name = "descripcion") String descripcion,
                                        HttpServletRequest request) {
        try {
            String emailAsoc = this.jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(archivoAsociacionService.uploadFile(file, nombre, descripcion, emailAsoc), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para eliminar una imagen por su ID
    @PreAuthorize("hasRole('ROLE_ASOC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImagen(@PathVariable Integer id) {
        try {
            archivoAsociacionService.deleteFile(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
