package com.preving.restapi.base.domain.dto;

import com.preving.restapi.base.domain.entity.Raza;
import com.preving.restapi.base.domain.entity.TipoAnimal;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Raza}
 */
@Data
public class RazaDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String nombre;
    @NotNull
    Integer idTipoAnimal;

    TipoAnimal tipoAnimal;

    public RazaDto(Integer id, String nombre, Integer idTipoAnimal, TipoAnimal tipoAnimal) {
        this.id = id;
        this.nombre = nombre;
        this.idTipoAnimal = idTipoAnimal;
        this.tipoAnimal = tipoAnimal;
    }

    public RazaDto() {

    }

    public Raza toEntity() {
        Raza raza = new Raza();
        raza.setId(this.getId());
        raza.setNombre(this.getNombre());
        raza.setIdTipoAnimal(this.getTipoAnimal()); // Asume que Raza tiene un campo TipoAnimal
        return raza;
    }
}