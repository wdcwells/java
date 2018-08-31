package com.wdc.test.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.wdc.test.api.DemoService;
import org.springframework.context.annotation.Profile;

/**
 * @author wdc
 * @date 2018/8/30
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Profile("provider")
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
