package com.subir.archivos.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServicesAPI_Implementation implements IFileServiceAPI {

    // Carpeta donde guardaremos los archivos
    private final Path rootFolder = Paths.get("uploads");
    
    
    @Override
    public void save(MultipartFile file) throws Exception {
        // Guardar el archivo en la carpeta uploads
        Files.copy(file.getInputStream(), rootFolder.resolve(file.getOriginalFilename()));
    }

    @Override
    public Resource load(String name) throws Exception {
        Path file = rootFolder.resolve(name);
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }

    @Override
    public void save(List<MultipartFile> files) throws Exception {
        // Guardar todos los archivos de la lista en la carpeta uploads
        for(MultipartFile file:files){
            save(file);
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        return Files.walk(rootFolder, 1).filter(
                path->!path.equals(rootFolder)).map(rootFolder::relativize);
    }
    
    
    
}
