package com.evg.restapi.base.domain.dto;

import com.evg.restapi.base.domain.entity.DocumentoAnimal;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.evg.restapi.base.domain.entity.DocumentoAnimal}
 */
@Value
public class DocumentoAnimalDto implements Serializable {
    Integer id;
    @NotNull
    AnimalDto idAnimal;
    @NotNull
    @Size(max = 255)
    String descripcion;
    @NotNull
    @Size(max = 90)
    String ficheroNombre;
    @NotNull
    @Size(max = 160)
    String ficheroRuta;
    @NotNull
    @Size(max = 90)
    String nombre;
    @NotNull
    Instant fechaCreacion;

    public DocumentoAnimalDto(DocumentoAnimal documentoAnimal) {
        this.id = documentoAnimal.getId();
        this.idAnimal = new AnimalDto(documentoAnimal.getIdAnimal());
        this.descripcion = documentoAnimal.getDescripcion();
        this.ficheroNombre = documentoAnimal.getFicheroNombre();
        this.ficheroRuta = documentoAnimal.getFicheroRuta();
        this.nombre = documentoAnimal.getNombre();
        this.fechaCreacion = documentoAnimal.getFechaCreacion();
    }

    public DocumentoAnimal toEntity() {
        DocumentoAnimal documentoAnimal = new com.evg.restapi.base.domain.entity.DocumentoAnimal();
        documentoAnimal.setId(this.getId());
        documentoAnimal.setIdAnimal(this.getIdAnimal().toEntity());
        documentoAnimal.setDescripcion(this.getDescripcion());
        documentoAnimal.setFicheroNombre(this.getFicheroNombre());
        documentoAnimal.setFicheroRuta(this.getFicheroRuta());
        documentoAnimal.setNombre(this.getNombre());
        documentoAnimal.setFechaCreacion(this.getFechaCreacion());
        return documentoAnimal;
    }
}
