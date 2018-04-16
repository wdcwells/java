package com.wdc.test.controllers;

import com.wdc.test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("upload")
    String upload(@RequestParam("file") MultipartFile file) {
        fileService.upload(file);
        return "上传成功";
    }

    @PostMapping("cardloan/test/file/upload")
    String testUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Path local = fileService.upload(file);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//        params.add("file", new FileSystemResource(local.toFile()));
        params.add("file", new ByteArrayResource(Files.readAllBytes(local)) {
            @Override
            public String getFilename() {
                return local.getFileName().toString();
            }
        });
        return restTemplate.postForObject("http://localhost:8888/file/upload", params, String.class);
    }


}
