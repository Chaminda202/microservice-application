package com.springboot.test.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/configurations")
public class ConfigController {
    @Autowired
    private Environment environment;

    @GetMapping
    public String variableValue(@RequestParam("keyValue") String key){
        return  this.environment.getProperty(key);
    }
}
