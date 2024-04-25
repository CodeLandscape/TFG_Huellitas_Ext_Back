package com.preving.restapi.base.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "animal_persona")
public class AnimalPersona {
    @EmbeddedId
    private AnimalPersonaId id;

    @MapsId("idPersona")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Persona", nullable = false)
    private Persona idPersona;

    @MapsId("idAnimal")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Animal", nullable = false)
    private Animal idAnimal;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Column(name = "estado")
    private Boolean estado;

}
