package com.syj.myshop.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/messages")
    public String[] getMessages() {
        return new String[] {"Message 1", "Message 2", "Message 3"};
    }
    @GetMapping("/api/public")
    public String publicResource() {
        return "Hello from a public endpoint! You don't need to be authenticated to see this.";
    }

    @GetMapping("/api/private")
    public String privateResource() {
        return "Hello from a private endpoint! You need to be authenticated to see this.";
    }
}
