package com.evg.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.evg.restapi.base.domain.entity.TipoAnimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link TipoAnimal}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        TipoAnimal tipoAnimal = new TipoAnimal();
        tipoAnimal.setId(this.getId());
        tipoAnimal.setNombre(this.getNombre());
        return tipoAnimal;
    }
}
