package com.test.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TContrller {

    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "hello world";
    }
}
