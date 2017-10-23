package com.wdc.test.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SwaggerBoot {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerBoot.class, args);
    }

    @Bean
    public ApiInfo get(){
        return new ApiInfoBuilder().build();
    }
}
