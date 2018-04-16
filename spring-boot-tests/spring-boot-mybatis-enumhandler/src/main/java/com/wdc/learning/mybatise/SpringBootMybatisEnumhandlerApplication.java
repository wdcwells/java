package com.wdc.learning.mybatise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
public class SpringBootMybatisEnumhandlerApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisEnumhandlerApplication.class, args);
	}

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        SortHandlerMethodArgumentResolver resolver = new SortHandlerMethodArgumentResolver();
        resolver.setSortParameter("springSort");
        argumentResolvers.add(resolver);
    }
}
