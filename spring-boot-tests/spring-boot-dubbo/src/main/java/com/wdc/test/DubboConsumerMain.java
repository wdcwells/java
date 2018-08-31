package com.wdc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wdc
 * @date 2018/8/30
 */
@SpringBootApplication
public class DubboConsumerMain {

    public static void main(String[] args) {
        args = new String[] {"--spring.profiles.active=consumer"};
        SpringApplication.run(DubboConsumerMain.class, args);
    }
}
