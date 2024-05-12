package com.preving.restapi.base.domain.entity;

import com.preving.restapi.base.security.Roles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Rol(Roles name) {
        this.name = name;
    }

    public Rol() {

    }
}
