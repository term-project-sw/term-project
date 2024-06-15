package com.example.airbnb.house.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class ImageEncoder {

    public String encodeFileToBase64Binary(Path path) throws IOException {
        byte[] fileContent = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
