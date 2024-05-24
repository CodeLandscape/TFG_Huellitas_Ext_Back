package com.evg.restapi.base.domain.dto;

import com.evg.restapi.base.domain.entity.Rol;
import com.evg.restapi.base.security.Roles;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link Rol}
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
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
