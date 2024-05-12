package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.AdminDto;
import com.preving.restapi.base.domain.dto.AsociacionDto;
import com.preving.restapi.base.domain.dto.PersonaDto;
import com.preving.restapi.base.domain.dto.UsuarioDto;
import com.preving.restapi.base.domain.entity.Admin;
import com.preving.restapi.base.domain.entity.Asociacion;
import com.preving.restapi.base.domain.entity.Persona;
import com.preving.restapi.base.domain.entity.Usuario;

public interface AuthService {
    public Persona addPerson(PersonaDto persona);

    public Asociacion addAsociacion(AsociacionDto asociacionDto);

    public Admin addAdmin(AdminDto adminDto);

    public String login(UsuarioDto usuario);
}
