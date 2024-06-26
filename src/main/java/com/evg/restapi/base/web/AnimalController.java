package com.evg.restapi.base.web;

import com.evg.restapi.base.domain.dto.AnimalDto;
import com.evg.restapi.base.domain.services.AnimalService;
import com.evg.restapi.base.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://tfg-huellitas-ext-front.onrender.com")
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(HttpServletRequest request, @RequestBody AnimalDto animal) {
        try {
            String email = jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(animalService.add(animal, email), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> fidndAll(@RequestParam(required = false) Boolean filtrarPorAsociacion,
                                      @RequestParam(required = false) String strSearch,
                                      @RequestParam(required = false) List<Long> idTipoAnimal,
                                      @RequestParam(required = false) List<Long> idRaza,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "6") int limit,
                                      @RequestParam(defaultValue = "id") String sort,
                                      @RequestParam(defaultValue = "asc") String order,
                                      HttpServletRequest request) {
        try {
            String email = jwtUtil.extractUsername(request.getHeader("Authorization").replace("Bearer ", ""));
            return new ResponseEntity<>(animalService.findAll(filtrarPorAsociacion, email, strSearch, idTipoAnimal, idRaza, page, limit, sort, order), HttpStatus.OK);
        } catch (
                Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AnimalDto animal) {
        try {
            animalService.update(id, animal);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/asociacion/{idAsociacion}")
    public List<AnimalDto> getAnimalsByAsociacionId(@PathVariable Integer idAsociacion) {
        return animalService.findByAsociacionId(idAsociacion);
    }

}
