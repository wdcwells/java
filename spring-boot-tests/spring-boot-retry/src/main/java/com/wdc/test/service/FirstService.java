package com.wdc.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author wdc
 * @date 2018/5/31
 */
@Service
public class FirstService {
    @Autowired
    private FirstServiceRetry firstServiceRetry;

    public int num() {
        try {
            System.out.println(retryExample1());
            return firstServiceRetry.retryExample2(this);
        } catch (Exception e) {
            return 0;
        }
    }

    private int retryExample1() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3));
        return retryTemplate.execute((RetryCallback<Integer, Exception>) context -> randomInt(),
                context -> {
                    System.out.printf("重试次数：%s, 重试原因：%s%n", context.getRetryCount(), context.getLastThrowable().getMessage());
                    return Integer.MAX_VALUE;
                });
    }

    int randomInt() {
        OptionalInt finded = ints().filter(i -> i > 98).findAny();
        if (finded.isPresent()) {
            int result = finded.getAsInt();
            return result;
        }
        throw new RuntimeException("找不到");
    }

    private IntStream ints() {
        Random random = new Random();
        IntStream ints = random.ints(5, 0, 100);
        return ints;
    }

}
