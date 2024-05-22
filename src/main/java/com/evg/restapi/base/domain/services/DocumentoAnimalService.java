package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.DocumentoAnimalDto;
import com.evg.restapi.base.domain.entity.DocumentoAnimal;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentoAnimalService {
    public byte[] findByArchivoId(Integer id);

    public DocumentoAnimalDto findInfoByArchivoId(Integer id);

    public DocumentoAnimalDto[] findInfoByAnimalId(Integer id);

    DocumentoAnimalDto uploadFile(MultipartFile file, String nombre, String descripcion, Integer idAnimal);

    void deleteArchivo(Integer id);

    public DocumentoAnimal editArchivo(Integer id, String nombre, String descripcion, MultipartFile file);
}
