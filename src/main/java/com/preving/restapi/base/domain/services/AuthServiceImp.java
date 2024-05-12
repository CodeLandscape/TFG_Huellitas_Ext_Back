package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.*;
import com.preving.restapi.base.domain.dto.AdminDto;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.dto.PersonaDto;
import com.preving.restapi.base.domain.dto.UsuarioDto;
import com.preving.restapi.base.domain.entity.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private AsociacionRepository asociacionRepository;

    @Override
    public Persona addPerson(PersonaDto personaDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = new Usuario();


        usuario.setCorreo(personaDto.getCorreo());
        usuario.setDireccion(personaDto.getDireccion());
        usuario.setActivo(true);
        usuario.setTlf(personaDto.getTlf());
        usuario.setIdRol(rolRepository.findByNombre("PERSONA"));
        usuario.setPassword(passwordEncoder.encode(personaDto.getPassword()));
        usuario.setPoblacion(personaDto.getPoblacion());
        usuario.setIdProvincia(provinciaRepository.findById(personaDto.getIdProvincia()).orElseThrow(() -> new RuntimeException("Provincia no encontrada")));

        usuario = usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setApellidos(personaDto.getApellidos());
        persona.setDni(personaDto.getDni());
        persona.setIdUsuario(usuario);

        return personaRepository.save(persona);
    }

    @Override
    public Asociacion addAsociacion(AsociacionDto asociacionDto) {

        Usuario usuario = new Usuario();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        usuario.setCorreo(asociacionDto.getCorreo());
        usuario.setDireccion(asociacionDto.getDireccion());
        usuario.setActivo(false); // Es false porque tiene que validarse por un administrador.
        usuario.setTlf(asociacionDto.getTlf());
        usuario.setIdRol(rolRepository.findByNombre("ASOCIACION"));
        usuario.setPassword(passwordEncoder.encode(asociacionDto.getPassword()));
        usuario.setPoblacion(asociacionDto.getPoblacion());
        usuario.setIdProvincia(provinciaRepository.findById(asociacionDto.getIdProvincia()).orElseThrow(() -> new RuntimeException("Provincia no encontrada")));

        usuario = usuarioRepository.save(usuario);

        Asociacion asociacion = new Asociacion();
        asociacion.setNombre(asociacionDto.getNombre());
        asociacion.setCif(asociacionDto.getCif());
        asociacion.setIdUsuario(usuario);



        return asociacionRepository.save(asociacion);
    }

    @Override
    public Admin addAdmin(AdminDto adminDto) {

        Usuario usuario = new Usuario();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        usuario.setCorreo(adminDto.getCorreo());
        usuario.setDireccion(adminDto.getDireccion());
        usuario.setActivo(true);
        usuario.setTlf(adminDto.getTlf());
        usuario.setIdRol(rolRepository.findByNombre("ADMIN"));
        usuario.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        usuario.setPoblacion(adminDto.getPoblacion());
        usuario.setIdProvincia(provinciaRepository.findById(adminDto.getIdProvincia()).orElseThrow(() -> new RuntimeException("Provincia no encontrada")));

        usuario = usuarioRepository.save(usuario);

        Admin admin = new Admin();
        admin.setNombre(adminDto.getNombre());
        admin.setApellidos(adminDto.getApellidos());
        admin.setIdUsuario(usuario);

        return adminRepository.save(admin);



    }

    @Override
    public String login(UsuarioDto usuario) {
        Usuario userInDB = usuarioRepository.findByCorreo(usuario.getCorreo());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (userInDB != null && passwordEncoder.matches(usuario.getPassword(), userInDB.getPassword())) {
            return Jwts.builder()
                    .setSubject(userInDB.getCorreo())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                    .signWith(SignatureAlgorithm.HS512, "YourSecretKey")
                    .compact();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
