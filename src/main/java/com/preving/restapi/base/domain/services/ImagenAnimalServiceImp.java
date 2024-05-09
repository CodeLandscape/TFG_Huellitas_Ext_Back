package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AnimalRepository;
import com.preving.restapi.base.domain.dao.ImagenAnimalRepository;
import com.preving.restapi.base.domain.dto.ImagenAnimalDto;
import com.preving.restapi.base.domain.entity.Animal;
import com.preving.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenAnimalServiceImp implements ImagenAnimalService {

    @Autowired
    private ImagenAnimalRepository imagenAnimalRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<ImagenAnimalDto> findByAnimalId(Integer id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            throw new IllegalArgumentException("No animal found with the given id");
        }
        List<ImagenAnimal> imagenAnimalList = this.imagenAnimalRepository.findByIdAnimal(animal);
        List<ImagenAnimalDto> imagenAnimalDtoList = new ArrayList<>();
        for (ImagenAnimal imagenAnimal : imagenAnimalList) {
            ImagenAnimalDto imagenAnimalDto = new ImagenAnimalDto(imagenAnimal);
            imagenAnimalDtoList.add(imagenAnimalDto);
        }
        return imagenAnimalDtoList;
    }

    @Override
    public ImagenAnimal uploadImage(MultipartFile file, Integer idAnimal) {
        // Verificar si los parámetros son válidos
        if (file == null || idAnimal == null) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        try {
            // Crear directorios si no existen
            Path filePath = new File("../archivos/animal/"+idAnimal).toPath();
            Files.createDirectories(filePath);
            // Usar "imagen" como nombre de archivo
            String uniqueFilename = "imagen";
            // Obtener la extensión del archivo
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // Combinar el nombre del archivo y la extensión
            String filenameWithExtension = uniqueFilename + extension;
            // Guardar el archivo en el sistema de archivos
            Files.copy(file.getInputStream(), filePath.resolve(filenameWithExtension), StandardCopyOption.REPLACE_EXISTING);
            // Obtener la ruta del archivo
            String rutaFichero = filePath.resolve(filenameWithExtension).toString();
            // Crear un nuevo documento de animal (imagen)
            ImagenAnimal imagenAnimal = new ImagenAnimal();
            imagenAnimal.setFicheroNombre(uniqueFilename);
            imagenAnimal.setFicheroRuta(rutaFichero);
            imagenAnimal.setFechaCreacion(Instant.now());
            imagenAnimal.setIdAnimal(animalRepository.findById(idAnimal).get());
            // Guardar el documento en la base de datos
            imagenAnimalRepository.save(imagenAnimal);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file");
        }
        return null;
    }

    @Override
    public void deleteImagen(Integer id) {
        ImagenAnimal imagenAnimal = imagenAnimalRepository.findById(id).get();
        File file = new File(imagenAnimal.getFicheroRuta());
        // Eliminar el archivo del sistema de archivos
        file.delete();
        // Eliminar el documento de la base de datos
        imagenAnimalRepository.delete(imagenAnimal);
    }

}
