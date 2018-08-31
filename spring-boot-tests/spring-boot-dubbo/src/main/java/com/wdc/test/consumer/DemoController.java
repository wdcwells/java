package com.wdc.test.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdc.test.api.DemoService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wdc
 * @date 2018/8/31
 */
@RestController
@Profile("consumer")
public class DemoController {
    @Reference(
            version = "1.0.0"
    )
    private DemoService demoService;

    @GetMapping("hi")
    String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
