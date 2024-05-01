package com.preving.restapi.base.domain.dto;

import com.preving.restapi.base.domain.entity.TipoAnimal;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.TipoAnimal}
 */
@Value
public class TipoAnimalDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String nombre;

    public TipoAnimalDto(TipoAnimal tipoAnimal) {
        this.id = tipoAnimal.getId();
        this.nombre = tipoAnimal.getNombre();
    }

    public TipoAnimal toEntity() {
        com.preving.restapi.base.domain.entity.TipoAnimal tipoAnimal = new com.preving.restapi.base.domain.entity.TipoAnimal();
        tipoAnimal.setId(this.getId());
        tipoAnimal.setNombre(this.getNombre());
        return tipoAnimal;
    }
}
