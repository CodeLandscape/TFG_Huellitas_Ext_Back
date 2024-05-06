package com.preving.restapi.base.domain.dto;

import com.preving.restapi.base.domain.entity.AnimalPersonaId;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AnimalPersonaDto {
    private AnimalPersonaId id;
    private PersonaDto idPersona;
    private AnimalDto idAnimal;
    private Instant fecha;
    private Boolean estado;
}