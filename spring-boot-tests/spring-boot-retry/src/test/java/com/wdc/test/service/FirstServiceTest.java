package com.wdc.test.service;

import com.wdc.test.RetryTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wdc
 * @date 2018/6/28
 */
public class FirstServiceTest extends RetryTests {
    @Autowired
    private FirstService service;

    @Test
    public void num() {
        System.out.println(service.num());
    }
}
