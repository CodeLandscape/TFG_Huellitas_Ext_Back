package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Asociacion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Asociacion}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsociacionDto implements Serializable {
    Integer id;
    @NotNull
    UsuarioDto usuario;
    @NotNull
    @Size(max = 90)
    String nombre;
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
