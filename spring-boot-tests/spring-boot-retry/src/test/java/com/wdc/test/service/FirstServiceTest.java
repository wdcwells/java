package com.wdc.test.service;

import com.wdc.test.RetryTests;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Arrays;

/**
 * @author wdc
 * @date 2018/6/28
 */
public class FirstServiceTest extends RetryTests {
    @Autowired
    private FirstService service;
    @Autowired
    private IFirstServiceRetry firstServiceRetry;
    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void num() {
        System.out.println(service.num());
    }

    @Test
    public void classType() throws Exception{
//        System.out.println(firstServiceRetry.getClass());
//        System.out.println(IFirstServiceRetry.class);
//        System.out.println(service.getClass());
        Arrays.stream(((DefaultListableBeanFactory) beanFactory).getSingletonNames())
                .map(name -> beanFactory.getBean(name).getClass())
                /*.filter(e -> e.contains("$$"))*/.forEach(System.out::println);
    }
}
