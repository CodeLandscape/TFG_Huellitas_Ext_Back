package com.preving.restapi.base.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_Provincia", nullable = false)
    private Provincia idProvincia;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_Rol", nullable = false)
    private Rol idRol;

    @Size(max = 60)
    @NotNull
    @Column(name = "correo", nullable = false, length = 60)
    private String correo;

    @Size(max = 100)
    @NotNull
    @Column(name = "poblacion", nullable = false, length = 100)
    private String poblacion;

    @Size(max = 200)
    @NotNull
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Size(max = 100)
    @NotNull
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Size(max = 9)
    @NotNull
    @Column(name = "tlf", nullable = false, length = 9)
    private String tlf;

    @NotNull
    @Column(name = "activo", nullable = false)
    private Boolean activo = false;


    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

}
