package com.wdc.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LoginController {
    final AtomicLong onlineCount = new AtomicLong();
    final static HashMap<String, String> userMap = new HashMap<>();
    final ConcurrentHashMap<String, String> userTokenMap = new ConcurrentHashMap<>();

    static {
        userMap.put("wdc", "123");
        userMap.put("wqh", "456");
    }

    @GetMapping("isLogin")
    boolean isLogin(HttpServletRequest request) {
        if (userTokenMap.containsKey(request.getRequestedSessionId())) return true;
        return false;
    }

    @PostMapping("login")
    String login(
            @RequestParam("name") String name
            , @RequestParam("pwd") String pwd
            , HttpServletRequest request
    ) {
        if (userMap.get(name).equals(pwd)) {
            String sid = getSid(request);
            userTokenMap.put(sid, name);
            return sid;
        } else throw new RuntimeException("user info wrong");
    }

    @PostMapping("logout")
    String logout(
            HttpServletRequest request
    ){
        userTokenMap.remove(getSid(request));
        request.getSession().invalidate();
        return "bye bye";
    }

    @GetMapping("statistics")
    Map<String, Object> statistics() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("onlineCount", onlineCount);
        result.put("tokens", userTokenMap);
        return result;
    }

    private String getSid(HttpServletRequest request) {
        return request.getSession().getId();
//        return ((Session) request.getSession()).getExtendedId();//jetty
    }
}
