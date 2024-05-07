package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.entity.DocumentoAnimal;
import com.preving.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagenAnimalService {
    public List<ImagenAnimal> findByAnimalId(Integer id);

    public ImagenAnimal uploadImage(MultipartFile file, Integer idAnimal);

    public void deleteImagen(Integer id);

    public ImagenAnimal editImagen(MultipartFile file, String name, String description, Integer id);
}
