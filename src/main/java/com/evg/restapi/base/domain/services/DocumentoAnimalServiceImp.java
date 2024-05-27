package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.AnimalRepository;
import com.evg.restapi.base.domain.dao.DocumentoAnimalRepository;
import com.evg.restapi.base.domain.dto.DocumentoAnimalDto;
import com.evg.restapi.base.domain.entity.Animal;
import com.evg.restapi.base.domain.entity.DocumentoAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.UUID;

@Service
public class DocumentoAnimalServiceImp implements DocumentoAnimalService {

    @Autowired
    private DocumentoAnimalRepository documentoAnimalRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public byte[] findByArchivoId(Integer id) {
        DocumentoAnimal documentoAnimal = documentoAnimalRepository.findById(id).orElse(null);
        if (documentoAnimal == null) {
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        Path path = Path.of(documentoAnimal.getFicheroRuta() + "/" + documentoAnimal.getFicheroNombre());
        try {
            return Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public DocumentoAnimalDto findInfoByArchivoId(Integer id) {
        DocumentoAnimal documentoAnimal = documentoAnimalRepository.findById(id).orElse(null);
        if (documentoAnimal == null) {
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        return new DocumentoAnimalDto(documentoAnimal);
    }

    @Override
    @Transactional
    public DocumentoAnimalDto[] findInfoByAnimalId(Integer id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            throw new IllegalArgumentException("Animal no encontrado");
        }
        DocumentoAnimal[] documentoAnimals = documentoAnimalRepository.findAllByIdAnimal(animal);
        DocumentoAnimalDto[] documentoAnimalDtos = new DocumentoAnimalDto[documentoAnimals.length];
        for (int i = 0; i < documentoAnimals.length; i++) {
            documentoAnimalDtos[i] = new DocumentoAnimalDto(documentoAnimals[i]);
        }
        return documentoAnimalDtos;
    }

    @Override
    @Transactional
    public DocumentoAnimalDto uploadFile(MultipartFile file, String nombre, String descripcion, Integer idAnimal) {
        if (file == null || idAnimal == null) {
            throw new IllegalArgumentException("Fichero o animal no encontrado");
        }
        Animal animal = animalRepository.findById(idAnimal).orElse(null);
        if (animal == null) {
            throw new IllegalArgumentException("Animal no encontrado");
        }
        try {
            Path path = Path.of("../archivos/animal/" + animal.getId() + "/documentos/");
            Files.createDirectories(path);

            String nombreFichero = UUID.randomUUID().toString();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String nombreFicheroFinal = nombreFichero + extension;
            Files.copy(file.getInputStream(), path.resolve(nombreFicheroFinal));

            DocumentoAnimal documentoAnimal = new DocumentoAnimal();
            documentoAnimal.setFicheroNombre(nombreFicheroFinal);
            documentoAnimal.setFicheroRuta(path.toString());
            documentoAnimal.setNombre(nombre);
            documentoAnimal.setDescripcion(descripcion);
            documentoAnimal.setIdAnimal(animal);
            documentoAnimal.setFechaCreacion(Instant.now());

            DocumentoAnimal documento = documentoAnimalRepository.save(documentoAnimal);
            return new DocumentoAnimalDto(documento);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteArchivo(Integer id) {
        DocumentoAnimal documentoAnimal = documentoAnimalRepository.findById(id).orElse(null);
        if (documentoAnimal == null) {
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        try {
            Path path = Path.of(documentoAnimal.getFicheroRuta() + "/" + documentoAnimal.getFicheroNombre());
            Files.delete(path);
            documentoAnimalRepository.delete(documentoAnimal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public DocumentoAnimal editArchivo(Integer id, String nombre, String descripcion, MultipartFile file) {
        DocumentoAnimal documentoAnimal = documentoAnimalRepository.findById(id).orElse(null);
        if (documentoAnimal == null) {
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        try {
            if (file != null) {
                Path path = Path.of(documentoAnimal.getFicheroRuta());
                Files.createDirectories(path);

                Path ExistingPath = Path.of(documentoAnimal.getFicheroRuta() + "/" + documentoAnimal.getFicheroNombre());
                if (Files.exists(ExistingPath)) {
                    Files.delete(ExistingPath);
                }

                String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                String nombreFichero = UUID.randomUUID().toString();
                String nombreFicheroFinal = nombreFichero + extension;

                Files.copy(file.getInputStream(), path.resolve(nombreFicheroFinal));
                documentoAnimal.setFicheroNombre(nombreFicheroFinal);
            }
            documentoAnimal.setNombre(nombre);
            documentoAnimal.setDescripcion(descripcion);
            return documentoAnimalRepository.save(documentoAnimal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
