package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsociacionDto {
    Integer id;
    @NotNull
    @Size(max = 45)
    String nombre;

    @NotNull
    String correo;
    @NotNull
    String password;
    @NotNull
    String tlf;
    @NotNull
    String direccion;
    @NotNull
    String poblacion;
    @NotNull
    Integer idProvincia;

    @NotNull
    @Size(max = 9)
    String cif;
}
