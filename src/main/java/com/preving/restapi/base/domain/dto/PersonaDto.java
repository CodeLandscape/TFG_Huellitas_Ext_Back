package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public PersonaDto(Persona persona){
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.apellidos = persona.getApellidos();
        this.dni = persona.getDni();
    }

    //convertir de personaDto a persona
    public Persona toEntity(){
        Persona persona = new Persona();
        persona.setId(this.id);
        persona.setNombre(this.nombre);
        persona.setApellidos(this.apellidos);
        persona.setDni(this.dni);
        return persona;
    }
}