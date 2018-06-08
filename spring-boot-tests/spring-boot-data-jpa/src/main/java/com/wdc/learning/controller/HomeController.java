package com.wdc.learning.controller;

import com.wdc.learning.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wdc
 * @date 2018/6/6
 */
@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    String home() throws Exception{
        homeService.testRepoTx();
//        homeService.testAsyncFind();
//        homeService.testTransactional();
//        System.out.println("paowanri");
//		homeService.testUpdateField();
        return "hello world";
    }
}
