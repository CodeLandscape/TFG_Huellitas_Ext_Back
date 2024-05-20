package com.evg.restapi.base.domain.services;

import com.evg.restapi.base.domain.dao.ArchivosAsociacionRepository;
import com.evg.restapi.base.domain.dao.AsociacionRepository;
import com.evg.restapi.base.domain.dao.UsuarioRepository;
import com.evg.restapi.base.domain.dto.ArchivosAsociacionDto;
import com.evg.restapi.base.domain.entity.ArchivosAsociacion;
import com.evg.restapi.base.domain.entity.Asociacion;
import com.evg.restapi.base.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.UUID;

@Service
public class ArchivoAsociacionServiceImp implements ArchivoAsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Autowired
    private ArchivosAsociacionRepository archivosAsociacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public byte[] findByArchivoId(Integer id) {
        ArchivosAsociacion archivosAsociacion = archivosAsociacionRepository.findById(id).orElse(null);
        if (archivosAsociacion == null) {
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        Path path = Path.of(archivosAsociacion.getFicheroRuta() + "/" + archivosAsociacion.getFicheroNombre());
        try {
            return Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public ArchivosAsociacionDto[] findInfoByAsociacionEmail (String emailAsociacion) {
        Asociacion asociacion = asociacionRepository.findByEmailUsuario(emailAsociacion);
        if (asociacion == null) {
            throw new IllegalArgumentException("Asociación no encontrada");
        }
        ArchivosAsociacion[] archivosAsociacion = archivosAsociacionRepository.findAllByIdAsociacion(asociacion);
        ArchivosAsociacionDto[] archivosAsociacionDtos = new ArchivosAsociacionDto[archivosAsociacion.length];
        for (int i = 0; i < archivosAsociacion.length; i++) {
            archivosAsociacionDtos[i] = new ArchivosAsociacionDto(archivosAsociacion[i]);
        }
        return archivosAsociacionDtos;
    }

    @Override
    public ArchivosAsociacion uploadFile(MultipartFile file, String nombre, String descripcion, String emailAsociacion) {
        if (file == null || emailAsociacion == null) {
            throw new IllegalArgumentException("Parámetros inválidos");
        }
        Asociacion asociacion = this.asociacionRepository.findByEmailUsuario(emailAsociacion);
        if (asociacion == null) {
            throw new IllegalArgumentException("Asociación no encontrada");
        }

        try {
            Path filePath = new File("../archivos/asociacion/" + asociacion.getIdUsuario().getId()).toPath();
            Files.createDirectories(filePath);

            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            String uniqueFileName = UUID.randomUUID().toString() + extension;

            Files.copy(file.getInputStream(), filePath.resolve(uniqueFileName));

            ArchivosAsociacion archivosAsociacion = new ArchivosAsociacion();
            archivosAsociacion.setNombre(nombre);
            archivosAsociacion.setDescripcion(descripcion);
            archivosAsociacion.setFicheroRuta(filePath.toString());
            archivosAsociacion.setFicheroNombre(uniqueFileName);
            archivosAsociacion.setIdAsociacion(asociacion);
            archivosAsociacion.setFechaCreacion(Instant.now());

            return archivosAsociacionRepository.save(archivosAsociacion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(Integer id) {
        ArchivosAsociacion archivosAsociacion = archivosAsociacionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));
        File file = new File(archivosAsociacion.getFicheroRuta() + "/" + archivosAsociacion.getFicheroNombre());
        file.delete();
        archivosAsociacionRepository.delete(archivosAsociacion);
    }
}
