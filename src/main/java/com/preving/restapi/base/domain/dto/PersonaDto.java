package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Persona;
import com.preving.restapi.base.domain.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Persona}
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaDto implements Serializable {
    Integer id;
    @NotNull
    UsuarioDto usuario;
    @NotNull
    @Size(max = 45)
    String nombre;
    @NotNull
    @Size(max = 90)
    String apellidos;
    @NotNull
    String correo;
    @NotNull
    String password;
    @NotNull
    String tlf;
    @NotNull
    String direccion;
    @NotNull
    String poblacion;
    @NotNull
    Integer idProvincia;

    @NotNull
    @Size(max = 9)
    String dni;


    //convertir de persona a personaDto
    public PersonaDto(Persona persona, Usuario usuario){
        this.id = persona.getId();
        this.usuario = new UsuarioDto(persona.getIdUsuario());
        this.nombre = persona.getNombre();
        this.apellidos = persona.getApellidos();
        this.dni = persona.getDni();
        this.correo = usuario.getCorreo();
        this.tlf = usuario.getTlf();
    }

    public PersonaDto(Persona persona) {
    }

    public Persona toEntity() {
        Persona persona = new Persona();
        persona.setId(this.getId());
        persona.setIdUsuario(this.getUsuario().toEntity());
        persona.setNombre(this.getNombre());
        persona.setApellidos(this.getApellidos());
        persona.setDni(this.getDni());
        return persona;
    }
}