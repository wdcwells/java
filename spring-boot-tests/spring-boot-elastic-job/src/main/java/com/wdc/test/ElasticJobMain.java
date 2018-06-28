package com.wdc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wdc
 * @date 2018/5/31
 */
@SpringBootApplication
@ImportResource({"classpath:elastic-job.xml"})
public class ElasticJobMain {
    public static void main(String[] args) {
        SpringApplication.run(ElasticJobMain.class, args);
    }
}
