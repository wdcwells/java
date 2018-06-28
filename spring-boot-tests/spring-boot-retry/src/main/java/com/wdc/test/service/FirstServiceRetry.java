package com.wdc.test.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author wdc
 * @date 2018/6/28
 */
@Service
public class FirstServiceRetry {


    @Retryable
    public int retryExample2(FirstService firstService) throws Exception {
        return firstService.randomInt();
    }

    @Recover
    public int retryExample2(Exception ex) {
        System.out.println(ex.getMessage());
        return Integer.MAX_VALUE;
    }
}
