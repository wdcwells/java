package com.wdc.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wdc
 * @date 2018/6/8
 */
@Async
@Service
public class AsyncService {
    @Autowired
    private HomeService homeService;

    public void testAsyncFind() {
        homeService.testAsyncFind();
    }

    public void testTransactional() throws Exception{
        homeService.testTransactional();
    }


}
