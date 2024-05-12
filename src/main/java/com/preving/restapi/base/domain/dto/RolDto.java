package com.preving.restapi.base.domain.dto;

import com.preving.restapi.base.domain.entity.Rol;
import com.preving.restapi.base.security.Roles;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Rol}
 */
@Value
public class RolDto implements Serializable {
    Integer id;
    @NotNull
    String nombre;

    public RolDto(Rol rol) {
        this.id = rol.getId();
        this.nombre = rol.getNombre().name();
    }

    public Rol toEntity() {
        Rol rol = new Rol();
        rol.setId(this.getId());
        rol.setNombre(Roles.valueOf(this.getNombre()));
        return rol;
    }
}
