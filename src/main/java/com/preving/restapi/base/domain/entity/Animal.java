package com.preving.restapi.base.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "fecha_Nac", nullable = false)
    private Instant fechaNac;

    @NotNull
    @Column(name = "fecha_Llegada_Asoc", nullable = false)
    private Instant fechaLlegadaAsoc;

    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Raza", nullable = false)
    private Raza idRaza;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Asociacion", nullable = false)
    private Asociacion idAsociacion;

    @NotNull
    @Column(name = "activo", nullable = false)
    private Boolean activo = false;

    @Column(name = "fecha_Adopcion")
    private Instant fechaAdopcion;

}
