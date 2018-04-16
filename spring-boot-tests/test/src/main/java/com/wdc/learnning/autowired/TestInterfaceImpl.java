package com.wdc.learnning.autowired;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(1)
public class TestInterfaceImpl implements TestInterface{

    @Override
    public void sayHello() {
        System.out.println("Hello");
    }
}
