package com.lottery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class HelloWorldController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getGreeting() {
        String message = "Hello Spring World!";
        return message;
    }

}
