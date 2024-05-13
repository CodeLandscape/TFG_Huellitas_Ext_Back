package com.evg.restapi.base.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Entidad implements Serializable {

    private int id;
    private String nombre;
    private boolean activo;

    public Entidad() {
    }

    public Entidad(int id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }
}
