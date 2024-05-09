package com.preving.restapi.base.domain.dto;

import com.preving.restapi.base.domain.entity.Animal;
import com.preving.restapi.base.domain.entity.ImagenAnimal;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
public class ImagenAnimalDto {
    Integer id;

    @NotNull
    Integer idAnimal;

    @Size(max = 90)
    @NotNull
    String ficheroNombre;

    @Size(max = 160)
    @NotNull
    String ficheroRuta;

    @NotNull
    Instant fechaCreacion;

    public ImagenAnimalDto(ImagenAnimal imagenAnimal) {
        this.id = imagenAnimal.getId();
        this.idAnimal = imagenAnimal.getIdAnimal().getId();
        this.ficheroNombre = imagenAnimal.getFicheroNombre();
        this.ficheroRuta = imagenAnimal.getFicheroRuta();
        this.fechaCreacion = imagenAnimal.getFechaCreacion();
    }

    public ImagenAnimal toEntity() {
        ImagenAnimal imagenAnimal = new ImagenAnimal();
        imagenAnimal.setId(this.getId());
        Animal animal = new Animal();
        animal.setId(this.getIdAnimal());
        imagenAnimal.setIdAnimal(animal);
        imagenAnimal.setFicheroNombre(this.getFicheroNombre());
        imagenAnimal.setFicheroRuta(this.getFicheroRuta());
        imagenAnimal.setFechaCreacion(this.getFechaCreacion());
        return imagenAnimal;
    }
}
