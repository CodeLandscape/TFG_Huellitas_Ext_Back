package com.evg.restapi.base.domain.dto;

import com.evg.restapi.base.domain.entity.ArchivosAsociacion;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
public class ArchivosAsociacionDto implements Serializable {
    Integer id;
    @NotNull
    AsociacionDto idAsociacion;
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

    public ArchivosAsociacionDto(ArchivosAsociacion archivosAsociacion) {
        this.id = archivosAsociacion.getId();
        this.idAsociacion = new AsociacionDto(archivosAsociacion.getIdAsociacion());
        this.descripcion = archivosAsociacion.getDescripcion();
        this.ficheroNombre = archivosAsociacion.getFicheroNombre();
        this.ficheroRuta = archivosAsociacion.getFicheroRuta();
        this.nombre = archivosAsociacion.getNombre();
        this.fechaCreacion = archivosAsociacion.getFechaCreacion();
    }

    public ArchivosAsociacion toEntity() {
        ArchivosAsociacion archivosAsociacion = new ArchivosAsociacion();
        archivosAsociacion.setId(this.getId());
        archivosAsociacion.setIdAsociacion(this.getIdAsociacion().toEntity());
        archivosAsociacion.setDescripcion(this.getDescripcion());
        archivosAsociacion.setFicheroNombre(this.getFicheroNombre());
        archivosAsociacion.setFicheroRuta(this.getFicheroRuta());
        archivosAsociacion.setNombre(this.getNombre());
        archivosAsociacion.setFechaCreacion(this.getFechaCreacion());
        return archivosAsociacion;
    }
}
