package com.wdc.test.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileService {
    Path upload(MultipartFile file);
}
