package com.preving.restapi.base.domain.services;

import com.preving.restapi.base.domain.dao.AnimalRepository;
import com.preving.restapi.base.domain.dao.ImagenAnimalRepository;
import com.preving.restapi.base.domain.dto.ImagenAnimalDto;
import com.preving.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public List<ImagenAnimal> findByAnimalId(Integer id) {
        List<ImagenAnimal> imagenAnimalList = this.imagenAnimalRepository.findByIdAnimal(animalRepository.findById(id).get());
        List<ImagenAnimalDto> imagenAnimalDtoList = new ArrayList<>();
        for (ImagenAnimal imagenAnimal : imagenAnimalList) {
            ImagenAnimalDto imagenAnimalDto = new ImagenAnimalDto(imagenAnimal);
            imagenAnimalDtoList.add(imagenAnimalDto);
        }
        return imagenAnimalList;
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
            // Generar nombre único para el archivo
            String uuid = UUID.randomUUID().toString();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String uniqueFilename = uuid + extension;
            // Guardar el archivo en el sistema de archivos
            Files.copy(file.getInputStream(), filePath.resolve(uniqueFilename));
            // Obtener la ruta del archivo
            String rutaFichero = filePath.resolve(uniqueFilename).toString();
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

    @Override
    public ImagenAnimal editImagen(MultipartFile file, String name, String description, Integer id) {
// Verificar si los parámetros son válidos
        if (file == null || name == null || description == null || id == null) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        try {
            // Crear directorios si no existen
            Path filePath = new File("../archivos/animal/"+id).toPath();
            Files.createDirectories(filePath);
            // Generar nombre único para el archivo
            String uuid = UUID.randomUUID().toString();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String uniqueFilename = uuid + extension;
            // Guardar el archivo en el sistema de archivos
            Files.copy(file.getInputStream(), filePath.resolve(uniqueFilename));
            // Obtener la ruta del archivo
            String rutaFichero = filePath.resolve(uniqueFilename).toString();
            // Crear un nuevo documento de animal (imagen)
            ImagenAnimal imagenAnimal = imagenAnimalRepository.findById(id).get();
            imagenAnimal.setFicheroNombre(uniqueFilename);
            imagenAnimal.setFicheroRuta(rutaFichero);
            // Guardar el documento en la base de datos
            imagenAnimalRepository.save(imagenAnimal);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file");
        }
        return null;
    }
}
