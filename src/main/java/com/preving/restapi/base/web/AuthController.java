package com.preving.restapi.base.web;
import com.preving.restapi.base.domain.dto.AdminDto;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.dto.PersonaDto;
import com.preving.restapi.base.domain.dto.UsuarioDto;
import com.preving.restapi.base.domain.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody PersonaDto persona) {
        try{
            return new ResponseEntity<>(authService.addPerson(persona), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminDto admin) {
        try{
            return new ResponseEntity<>(authService.addAdmin(admin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register-association")
    public ResponseEntity<?> registerAssociation(@RequestBody AsociacionDto asociacion) {
        try{
            return new ResponseEntity<>(authService.addAsociacion(asociacion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}