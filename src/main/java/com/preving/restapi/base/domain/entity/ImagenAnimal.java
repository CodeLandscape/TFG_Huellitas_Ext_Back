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
@Table(name = "imagen_animal")
public class ImagenAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Animal", nullable = false)
    private Animal idAnimal;

    @Size(max = 90)
    @NotNull
    @Column(name = "fichero_Nombre", nullable = false, length = 90)
    private String ficheroNombre;

    @Size(max = 160)
    @NotNull
    @Column(name = "fichero_Ruta", nullable = false, length = 160)
    private String ficheroRuta;

    @NotNull
    @Column(name = "fecha_Creacion", nullable = false)
    private Instant fechaCreacion;

}
