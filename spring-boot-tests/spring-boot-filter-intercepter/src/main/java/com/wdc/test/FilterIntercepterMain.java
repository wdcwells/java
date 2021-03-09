package com.wdc.test;

import com.wdc.test.interceptors.HelloInterceptor;
import com.wdc.test.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.Validator;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;

@SpringBootApplication
@ServletComponentScan
public class FilterIntercepterMain extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(FilterIntercepterMain.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HelloInterceptor()).addPathPatterns("/hello/*");
    }
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(@Qualifier("mvcContentNegotiationManager") ContentNegotiationManager contentNegotiationManager, @Qualifier("mvcConversionService") FormattingConversionService conversionService, @Qualifier("mvcValidator") Validator validator) {
        // Create or delegate to "super" to create and
        // customize properties of RequestMappingHandlerAdapter
        RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter(contentNegotiationManager, conversionService, validator);
        adapter.setResponseBodyAdvice(Collections.singletonList(new ResponseBodyAdvice<Object>() {

            @Override
            public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
                return true;
            }

            @Override
            public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
                ServletServerHttpRequest req = (ServletServerHttpRequest) request;
                System.out.println(request.getURI().getPath());
                System.out.println(JsonUtil.toJson(body));
                return body;
            }
        }));
//        List<HandlerMethodReturnValueHandler> valueHandlers = adapter.getCustomReturnValueHandlers();
//        valueHandlers.add(null);
//        adapter.setCustomReturnValueHandlers(valueHandlers);
        return adapter;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private SimpleClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setBufferRequestBody(false);
        clientHttpRequestFactory.setConnectTimeout(1 * 60 * 1000);//1分钟
        clientHttpRequestFactory.setReadTimeout(1*60*1000);
        return clientHttpRequestFactory;
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate() {
        SimpleClientHttpRequestFactory asyncRequestFactory = clientHttpRequestFactory();
        asyncRequestFactory.setTaskExecutor(taskExecutor());
        return new AsyncRestTemplate(asyncRequestFactory);
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(10);
        return taskExecutor;
    }


}
