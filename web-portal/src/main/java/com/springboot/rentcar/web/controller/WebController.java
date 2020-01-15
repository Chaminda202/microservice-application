package com.springboot.rentcar.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String homePage(){
        return "home";
    }

    @GetMapping(value = "/secure")
    public String securePage(){
        return "secure";
    }
}
