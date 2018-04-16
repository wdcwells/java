package com.wdc.test.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("hello.text")
    public String get() {
        return "Hello";
    }


    @GetMapping("hello.json")
    @ApiOperation(value = "获取json", produces = "application/json")
    public HelloJson getJson() {
        return new HelloJson(1, "Hello World");
    }

    @ApiModel("你好json")
    private static class HelloJson {
        @ApiModelProperty("主键")
        private Integer id;
        @ApiModelProperty("消息")
        private String msg;

        public HelloJson() {
        }

        public HelloJson(Integer id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
