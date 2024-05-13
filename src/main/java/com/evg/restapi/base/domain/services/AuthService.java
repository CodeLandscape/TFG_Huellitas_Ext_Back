package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.AdminDto;
import com.evg.restapi.base.domain.dto.AsociacionDto;
import com.evg.restapi.base.domain.dto.PersonaDto;
import com.evg.restapi.base.domain.dto.UsuarioDto;
import com.evg.restapi.base.domain.entity.Admin;
import com.evg.restapi.base.domain.entity.Asociacion;
import com.evg.restapi.base.domain.entity.Persona;

public interface AuthService {
    public Persona addPerson(PersonaDto persona);

    public Asociacion addAsociacion(AsociacionDto asociacionDto);

    public Admin addAdmin(AdminDto adminDto);

    public String login(UsuarioDto usuario);
}
