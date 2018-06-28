package com.wdc.test.controller;

import com.wdc.test.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wdc
 * @date 2018/5/31
 */
@RestController
@Validated
public class FirstController {
    @Autowired
    private FirstService firstService;

    @GetMapping("num")
    int num() {
        return firstService.num();
    }
}
