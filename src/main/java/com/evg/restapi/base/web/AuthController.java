package com.evg.restapi.base.web;
import com.evg.restapi.base.domain.dto.*;
import com.evg.restapi.base.domain.services.AuthService;
import com.evg.restapi.base.domain.services.UsuarioService;
import com.evg.restapi.base.exceptions.CustomRestApiException;
import com.evg.restapi.base.exceptions.errors.RestApiErrorCode;
import com.evg.restapi.base.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDto usuarioDto) {
        try {
            JwtResponse jwt = authService.login(usuarioDto);
            UsuarioDto usuario = usuarioService.findByCorreo(usuarioDto.getCorreo());
            JwtDTO jwtDTO = new JwtDTO(jwt.getToken(), usuario.getCorreo(), usuario.getId(), usuario.getRol().getNombre());
            return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
        } catch (CustomRestApiException e) {
            throw new CustomRestApiException(HttpStatus.BAD_REQUEST, e.getError().getCode(), "Usuario inactivo");
        }
        catch (Exception e) {
            throw new CustomRestApiException(HttpStatus.BAD_REQUEST, RestApiErrorCode.USER_INCORRECT, "Usuario o contraseña incorrectos");
        }
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody PersonaDto persona) {
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
