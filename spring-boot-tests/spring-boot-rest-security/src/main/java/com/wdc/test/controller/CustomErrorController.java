package com.wdc.test.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangdachong on 2017/3/10.
 */
@RestController
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("error")
    public Map<String, Object> errorResponse(HttpServletRequest request) {
        return getErrorAttributes(request, false);
    }
}
