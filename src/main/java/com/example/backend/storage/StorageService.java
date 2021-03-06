package com.example.backend.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    Path store(MultipartFile file);

    Path initializeProjectFolder(String simulationId);

    Stream<Path> loadAll();

    Path load(String folder,String filename);

    Resource loadAsResource(String folder, String filename);

    void deleteAll();

}
