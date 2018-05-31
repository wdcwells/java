package com.wdc.test.controller;

import com.wdc.test.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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

    @GetMapping("validate")
    void validate(Pojo pojo) {
        firstService.validate(pojo);
    }

    public static class Pojo {
        private String f1;

        public @NotNull String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }
    }
}
