package com.wdc.test.service;

import com.wdc.test.controller.FirstController;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author wdc
 * @date 2018/5/31
 */
@Service
@Validated
public class FirstService {

    public int num() {
        try {
            return retryExample1();
        } catch (Exception e) {
            return 0;
        }
    }

    private int retryExample1() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3));
        return retryTemplate.execute((RetryCallback<Integer, Exception>) context -> {
            System.out.println("重试次数：" + context.getRetryCount());
            return randomInt();
        }, context -> Integer.MAX_VALUE);
    }

    private int randomInt() {
        OptionalInt finded = ints().filter(i -> i > 95).findAny();
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

    public void validate(@NotNull FirstController.Pojo pojo) {
        System.out.println("do nothing");
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<FirstController.Pojo>> validate = validator.validate(pojo);
//        validate.forEach(e -> System.out.println(e.getMessage()));
    }
}
