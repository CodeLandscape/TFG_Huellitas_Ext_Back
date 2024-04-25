package com.preving.restapi.base.domain.dto;

import com.preving.restapi.base.domain.entity.Persona;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Persona}
 */
@Value
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
    @Size(max = 9)
    String dni;

    public PersonaDto(Persona persona) {
        this.id = persona.getId();
        this.usuario = new UsuarioDto(persona.getIdUsuario());
        this.nombre = persona.getNombre();
        this.apellidos = persona.getApellidos();
        this.dni = persona.getDni();
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
