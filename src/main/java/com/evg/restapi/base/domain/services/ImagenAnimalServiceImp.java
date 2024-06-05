package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.AnimalRepository;
import com.evg.restapi.base.domain.dao.ImagenAnimalRepository;
import com.evg.restapi.base.domain.dto.ImagenAnimalDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.ImagenAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.List;

@Service
public class ImagenAnimalServiceImp implements ImagenAnimalService {

    @Autowired
    private ImagenAnimalRepository imagenAnimalRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public byte[] findByAnimalId(Integer id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            throw new IllegalArgumentException("No animal found with the given id");
        }
        List<ImagenAnimal> imagenAnimalList = this.imagenAnimalRepository.findByIdAnimal(animal);
        if (imagenAnimalList.isEmpty()) {
            throw new IllegalArgumentException("No image found for the given animal id");
        }
        // Aquí asumimos que quieres devolver la primera imagen si hay varias
        ImagenAnimal imagenAnimal = imagenAnimalList.get(0);
        Path path = Paths.get(imagenAnimal.getFicheroRuta());
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImagenAnimal uploadImage(MultipartFile file, Integer idAnimal) {
        // Verificar si los parámetros son válidos
        if (file == null || idAnimal == null) {
            throw new IllegalArgumentException("Parámetros inválidos");
        }
        try {
            // Buscar el animal
            Animal animal = animalRepository.findById(idAnimal).orElseThrow(() -> new IllegalArgumentException("No se encontró un animal con el id dado"));

            // Buscar las imágenes existentes para el animal
            List<ImagenAnimal> existingImages = imagenAnimalRepository.findByIdAnimal(animal);

            // Eliminar cada imagen existente del sistema de archivos y de la base de datos
            for (ImagenAnimal existingImage : existingImages) {
                File existingFile = new File(existingImage.getFicheroRuta());
                existingFile.delete();
                imagenAnimalRepository.delete(existingImage);
            }

            // Crear directorios si no existen
            Path filePath = new File("../archivos/animal/"+idAnimal).toPath();
            Files.createDirectories(filePath);

            // Usar "imagen" como el nombre del archivo
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
            imagenAnimal.setIdAnimal(animal);

            // Guardar el documento en la base de datos
            imagenAnimalRepository.save(imagenAnimal);
        } catch (Exception e) {
            throw new RuntimeException("Error al subir el archivo");
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
    public ImagenAnimalDto findInfoByAnimalId(Integer id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            throw new IllegalArgumentException("No animal found with the given id");
        }
        List<ImagenAnimal> imagenAnimalList = this.imagenAnimalRepository.findByIdAnimal(animal);
        return new ImagenAnimalDto(imagenAnimalList.get(0));
    }

}
