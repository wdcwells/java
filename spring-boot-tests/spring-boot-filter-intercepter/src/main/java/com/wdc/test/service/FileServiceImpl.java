package com.wdc.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.rootLocation:/tmp/upload-dir}")
    private String rootLocation;

    @Override
    public Path upload(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path rootPath;
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            if (!(rootPath = Paths.get(rootLocation)).toFile().exists()) {
                init(rootPath);
            }
            Path resolvedPath = rootPath.resolve(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_").format(LocalDateTime.now()) + filename);
            Files.copy(file.getInputStream(), resolvedPath,
                    StandardCopyOption.REPLACE_EXISTING);
            return resolvedPath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file " + filename, e);
        }
    }

    private void init(Path rootPath) {
        try {
            Files.createDirectories(rootPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize root path", e);
        }
    }
}
