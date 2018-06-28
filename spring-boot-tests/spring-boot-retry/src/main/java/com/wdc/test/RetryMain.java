package com.wdc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author wdc
 * @date 2018/5/31
 */
@SpringBootApplication
@EnableRetry
public class RetryMain {
    public static void main(String[] args) {
        SpringApplication.run(RetryMain.class, args);
    }
}
