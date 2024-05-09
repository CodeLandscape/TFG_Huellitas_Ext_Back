package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dto.ImagenAnimalDto;
import com.preving.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagenAnimalService {
    public byte[] findByAnimalId(Integer id);

    public ImagenAnimal uploadImage(MultipartFile file, Integer idAnimal);

    public void deleteImagen(Integer id);

    public ImagenAnimalDto findInfoByAnimalId(Integer id);
}
