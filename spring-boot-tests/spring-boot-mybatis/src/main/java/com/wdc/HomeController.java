package com.wdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangdachong on 2017/4/14.
 */
@RestController
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String hello() {
        logger.info("requesting for home");
        return "hello";
    }
}
