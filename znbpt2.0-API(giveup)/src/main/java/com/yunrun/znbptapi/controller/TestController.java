package com.yunrun.znbptapi.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FrankChen on 2018/1/2.
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String heelo(){
        return "123";
    }
}
