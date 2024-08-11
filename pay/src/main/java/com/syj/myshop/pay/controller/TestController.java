package com.syj.myshop.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class TestController {

    @Value("${a}")
    String a;

    @RequestMapping("test")
    public String test(){
        return a;
    }
}
