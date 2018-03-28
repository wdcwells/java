package com.weiyan.cardloan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.weiyan.cardloan.dubbo.demo.RequestResult;
import com.weiyan.cardloan.dubbo.service.OpenAssetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDubboApplication implements CommandLineRunner {

    @Reference
    private OpenAssetService openAssetService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RequestResult wash = openAssetService.wash();
        System.out.println(wash.getCode() + ":" + wash.getMessage());
    }
}
