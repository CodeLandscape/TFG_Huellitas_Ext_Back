package com.evg.restapi.base.domain.dto;

import com.evg.restapi.base.domain.entity.Asociacion;
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
    UsuarioDto usuario;

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

    public AsociacionDto(Asociacion asociacion) {
        this.id = asociacion.getId();
        this.usuario = new UsuarioDto(asociacion.getIdUsuario());
        this.nombre = asociacion.getNombre();
        this.cif = asociacion.getCif();
    }

    public Asociacion toEntity() {
        Asociacion asociacion = new Asociacion();
        asociacion.setId(this.getId());
        asociacion.setIdUsuario(this.getUsuario().toEntity());
        asociacion.setNombre(this.getNombre());
        asociacion.setCif(this.getCif());
        return asociacion;
    }
}
