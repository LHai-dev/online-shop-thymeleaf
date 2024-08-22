package com.example.sa.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
   void init();

    String save(MultipartFile file) throws IOException;

   Resource load(String filename);

   boolean delete(String filename);
  
   void deleteAll();

   Stream<Path> loadAll();
}