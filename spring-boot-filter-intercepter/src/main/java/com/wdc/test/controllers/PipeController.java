package com.wdc.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("pipe")
public class PipeController {
    private final RestTemplate restTemplate;
    private final AsyncRestTemplate asyncRestTemplate;

    @Autowired
    public PipeController(RestTemplate restTemplate, AsyncRestTemplate asyncRestTemplate) {
        this.restTemplate = restTemplate;
        this.asyncRestTemplate = asyncRestTemplate;
    }

    @GetMapping("test/baidu")
    String testRest() {
        return restTemplate.getForObject("https://www.baidu.com", String.class);
    }

    @GetMapping("test/async/baidu")
    ResponseEntity<String> testRestAsync() {
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.getForEntity("https://www.baidu.com", String.class);
        long before = System.currentTimeMillis();
        while (!future.isDone()) {
            System.out.println("处理中………………");
        }
        long after = System.currentTimeMillis();
        System.out.println("耗时毫秒：" + (after - before));
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }



}
