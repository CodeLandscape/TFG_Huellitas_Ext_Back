package com.preving.restapi.base.domain.dto;

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
}