package com.wdc.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by wangdachong on 2017/3/7.
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "welcome home!!!";
    }

    @RequestMapping("hello")
    public HelloMsg hello(HttpServletRequest request, Principal principal) {
        return new HelloMsg(
                null != principal ? principal.getName() : "匿名",
                "hello",
                request.getRemoteAddr()
        );
    }

    @RequestMapping("hello/intro")
    public HelloMsg findUsers(String name, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (null == name) response.sendError(400, "姓名不能为空");
        return new HelloMsg(
                name,
                "hello",
                request.getRemoteAddr()
        );
    }

    private static class HelloMsg {
        private String name;
        private String msg;
        private String ip;

        public HelloMsg() {
        }

        public HelloMsg(String name, String msg, String ip) {
            this.name = name;
            this.msg = msg;
            this.ip = ip;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
