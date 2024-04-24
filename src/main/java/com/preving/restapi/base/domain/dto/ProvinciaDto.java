package com.preving.restapi.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.preving.restapi.base.domain.entity.Provincia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.preving.restapi.base.domain.entity.Provincia}
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProvinciaDto implements Serializable {
    Byte id;
    @NotNull
    @Size(max = 200)
    String nombre;

    public ProvinciaDto(Provincia provincia) {
        this.id = provincia.getId();
        this.nombre = provincia.getNombre();
    }

    public Provincia toEntity() {
        Provincia provincia = new Provincia();
        provincia.setId(this.getId());
        provincia.setNombre(this.getNombre());
        return provincia;
    }
}
