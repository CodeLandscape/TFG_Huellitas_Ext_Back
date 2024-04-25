package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Usuario}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto implements Serializable {
    Integer id;
    @NotNull
    ProvinciaDto provincia;
    @NotNull
    RolDto rol;
    @NotNull
    @Size(max = 60)
    String correo;
    @NotNull
    @Size(max = 100)
    String poblacion;
    @NotNull
    @Size(max = 200)
    String password;
    @NotNull
    @Size(max = 100)
    String direccion;
    @NotNull
    @Size(max = 9)
    String tlf;
    @NotNull
    Boolean activo;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.provincia = new ProvinciaDto(usuario.getIdProvincia());
        this.rol = new RolDto(usuario.getIdRol());
        this.correo = usuario.getCorreo();
        this.poblacion = usuario.getPoblacion();
        this.password = usuario.getPassword();
        this.direccion = usuario.getDireccion();
        this.tlf = usuario.getTlf();
        this.activo = usuario.getActivo();
    }

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.getId());
        usuario.setIdProvincia(this.getProvincia().toEntity());
        usuario.setIdRol(this.getRol().toEntity());
        usuario.setCorreo(this.getCorreo());
        usuario.setPoblacion(this.getPoblacion());
        usuario.setPassword(this.getPassword());
        usuario.setDireccion(this.getDireccion());
        usuario.setTlf(this.getTlf());
        usuario.setActivo(this.getActivo());
        return usuario;
    }
}