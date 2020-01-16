package com.springboot.rentcar.web.controller;

import com.springboot.rentcar.common.model.Customer;
import com.springboot.rentcar.web.thirdparty.ProfileServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class WebController {
    private ProfileServiceClient profileServiceClient;
    @GetMapping(value = "/")
    public String homePage(){
        return "home";
    }

    @GetMapping(value = "/secure")
    public String securePage(){
        return "secure";
    }

    @GetMapping(value = "/customers")
    public String displayAllCustomers(Model model){
        List<Customer> customerList = profileServiceClient.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "secure";
    }
}
