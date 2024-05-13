package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dto.ImagenAnimalDto;
import com.evg.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenAnimalService {
    public byte[] findByAnimalId(Integer id);

    public ImagenAnimal uploadImage(MultipartFile file, Integer idAnimal);

    public void deleteImagen(Integer id);

    public ImagenAnimalDto findInfoByAnimalId(Integer id);
}
