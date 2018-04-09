package com.weiyan.cardloan.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.weiyan.cardloan.dubbo.service.HelloService;

/**
 * @author wdc
 * @date 2018/3/28
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String getHello(String msg) {
        return "server:" + msg;
    }
}
