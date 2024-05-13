package com.evg.restapi.base.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "archivos_asociacion")
public class ArchivosAsociacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Asociacion", nullable = false)
    private Asociacion idAsociacion;

    @Size(max = 255)
    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Size(max = 90)
    @NotNull
    @Column(name = "fichero_Nombre", nullable = false, length = 90)
    private String ficheroNombre;

    @Size(max = 160)
    @NotNull
    @Column(name = "fichero_Ruta", nullable = false, length = 160)
    private String ficheroRuta;

    @Size(max = 90)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 90)
    private String nombre;

    @NotNull
    @Column(name = "fechaCreacion", nullable = false)
    private Instant fechaCreacion;

}
