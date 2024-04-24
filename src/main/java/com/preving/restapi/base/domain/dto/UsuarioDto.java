package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Provincia;
import com.preving.restapi.base.domain.entity.Rol;
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
    Provincia idProvincia;
    @NotNull
    Rol idRol;
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
    Boolean activo;

    //convertir de usuario a usuarioDto
    public UsuarioDto(Usuario usuario){
        this.id = usuario.getId();
        this.idProvincia = usuario.getIdProvincia();
        this.idRol = usuario.getIdRol();
        this.correo = usuario.getCorreo();
        this.poblacion = usuario.getPoblacion();
        this.password = usuario.getPassword();
        this.direccion = usuario.getDireccion();
        this.activo = usuario.getActivo();
    }

    //convertir de usuarioDto a usuario
    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setIdProvincia(this.idProvincia);
        usuario.setIdRol(this.idRol);
        usuario.setCorreo(this.correo);
        usuario.setPoblacion(this.poblacion);
        usuario.setPassword(this.password);
        usuario.setDireccion(this.direccion);
        usuario.setActivo(this.activo);
        return usuario;
    }
}