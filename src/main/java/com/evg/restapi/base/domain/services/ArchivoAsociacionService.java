package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.ArchivosAsociacionDto;
import com.evg.restapi.base.domain.entity.ArchivosAsociacion;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoAsociacionService {
    public byte[] findByArchivoId(Integer id);

    public ArchivosAsociacionDto[] findInfoByAsociacionEmail(String emailAsociacion);

    public ArchivosAsociacion uploadFile(MultipartFile file, String nombre, String descripcion, String emailAsociacion);

    public void deleteFile(Integer id);
}
