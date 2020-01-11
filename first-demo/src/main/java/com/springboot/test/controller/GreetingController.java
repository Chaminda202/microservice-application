package com.springboot.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping(value = "/greetings")
    public String greeting(@RequestParam("name") String name, @RequestParam("gender") String gender){
        if("Male".equals(gender)){
            return String.format("Hello Mr. %s. How are you?", name);
        }else{
            return String.format("Hello Mrs. %s. How are you?", name);
        }
    }
}
