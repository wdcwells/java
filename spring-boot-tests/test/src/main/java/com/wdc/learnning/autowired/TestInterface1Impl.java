package com.wdc.learnning.autowired;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class TestInterface1Impl implements TestInterface{

    @Override
    public void sayHello() {
        System.out.println("world");
    }
}
