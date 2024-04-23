package com.preving.restapi.base.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "asociacion")
public class Asociacion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Usuario", nullable = false)
    private Usuario idUsuario;

    @Size(max = 90)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 90)
    private String nombre;

    @Size(max = 9)
    @NotNull
    @Column(name = "cif", nullable = false, length = 9)
    private String cif;

    @NotNull
    @Column(name = "activo", nullable = false)
    private Boolean activo = false;

}
