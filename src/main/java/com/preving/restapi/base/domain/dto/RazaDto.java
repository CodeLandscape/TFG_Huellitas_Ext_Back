package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Raza;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Raza}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RazaDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String nombre;
    @NotNull
    TipoAnimalDto tipoAnimal;

    public RazaDto(Raza raza) {
        this.id = raza.getId();
        this.nombre = raza.getNombre();
        this.tipoAnimal = new TipoAnimalDto(raza.getIdTipoAnimal());
    }

    public Raza toEntity() {
        Raza raza = new Raza();
        raza.setId(this.getId());
        raza.setNombre(this.getNombre());
        raza.setIdTipoAnimal(this.getTipoAnimal().toEntity());
        return raza;
    }
}
