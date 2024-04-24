package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Rol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Rol}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolDto implements Serializable {
    Byte id;
    @NotNull
    @Size(max = 200)
    String nombre;

    public RolDto(Rol rol) {
        this.id = rol.getId();
        this.nombre = rol.getNombre();
    }

    public Rol toEntity() {
        Rol rol = new Rol();
        rol.setId(this.getId());
        rol.setNombre(this.getNombre());
        return rol;
    }
}
