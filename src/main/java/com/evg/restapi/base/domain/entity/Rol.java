package com.evg.restapi.base.domain.entity;

import com.evg.restapi.base.security.Roles;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre", nullable = false, length = 200)
    private Roles nombre;

}
