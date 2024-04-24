package com.preving.restapi.base.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AnimalPersonaId implements Serializable {
    private static final long serialVersionUID = -3506650880092598253L;
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Persona", nullable = false)
    private Integer idPersona;

    @NotNull
    @Column(name = "id_Animal", nullable = false)
    private Integer idAnimal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalPersonaId entity = (AnimalPersonaId) o;
        return Objects.equals(this.idAnimal, entity.idAnimal) &&
               Objects.equals(this.idPersona, entity.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, idPersona);
    }

}
