package com.evg.restapi.base.domain.dto;

import com.evg.restapi.base.domain.entity.Raza;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link Raza}
 */
@Value
public class RazaDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String nombre;
    @NotNull
    Integer idTipoAnimal;

    public RazaDto(Integer id, String nombre, Integer idTipoAnimal) {
        this.id = id;
        this.nombre = nombre;
        this.idTipoAnimal = idTipoAnimal;
    }

    public Raza toEntity() {
        Raza raza = new Raza();
        raza.setId(this.getId());
        raza.setNombre(this.getNombre());
        return raza;
    }
}