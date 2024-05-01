package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

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
    Date fechaNac;
    @NotNull
    Date fechaLlegadaAsoc;
    @Size(max = 255)
    String observaciones;
    @NotNull
    RazaDto raza;
    @NotNull
    AsociacionDto asociacion;
    @NotNull
    Boolean activo;
    Instant fechaAdopcion;

    public AnimalDto(Animal animal) {
        this.id = animal.getId();
        this.nombre = animal.getNombre();
        this.fechaNac = Date.from(animal.getFechaNac());
        this.fechaLlegadaAsoc = Date.from(animal.getFechaLlegadaAsoc());
        this.observaciones = animal.getObservaciones();
        this.raza = new RazaDto(animal.getIdRaza());
        this.asociacion = new AsociacionDto(animal.getIdAsociacion());
        this.activo = animal.getActivo();
        this.fechaAdopcion = animal.getFechaAdopcion();
    }

    public Animal toEntity() {
        Animal animal = new Animal();
        animal.setId(this.getId());
        animal.setNombre(this.getNombre());
        animal.setFechaNac(this.getFechaNac().toInstant());
        animal.setFechaLlegadaAsoc(this.getFechaLlegadaAsoc().toInstant());
        animal.setObservaciones(this.getObservaciones());
        animal.setIdRaza(this.getRaza().toEntity());
        animal.setIdAsociacion(this.getAsociacion().toEntity());
        animal.setActivo(this.getActivo());
        animal.setFechaAdopcion(this.getFechaAdopcion());
        return animal;
    }

}
