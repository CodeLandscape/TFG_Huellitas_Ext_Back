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
import java.time.Instant;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Animal}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String nombre;
    @NotNull
    Instant fechaNac;
    @NotNull
    Instant fechaLlegadaAsoc;
    @Size(max = 255)
    String observaciones;
    @NotNull
    Raza idRaza;
    @NotNull
    AsociacionDto idAsociacion;
    @NotNull
    Boolean activo;
    Instant fechaAdopcion;
}