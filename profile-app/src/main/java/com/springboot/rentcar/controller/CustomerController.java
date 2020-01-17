package com.springboot.rentcar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.rentcar.common.model.Customer;
import com.springboot.rentcar.mapper.AccessToken;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return ResponseEntity.ok().body(this.customerService.create(customer));
    }

    @GetMapping(value = "/{dlNumber}")
    public ResponseEntity<Customer> findByDlNumber(@PathVariable("dlNumber") String dlNumber){
        return ResponseEntity.ok().body(this.customerService.findByDlNumber(dlNumber));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('read_profile')")
    public ResponseEntity<List<Customer>> findAll() throws JsonProcessingException {
        AccessToken accessToken = (AccessToken)((OAuth2AuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails()).getDecodedDetails();
        OAuth2AuthenticationDetails authenticationDetails= (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        ObjectMapper Obj = new ObjectMapper();
        System.out.println("Before print the access token details");
        System.out.println(Obj.writeValueAsString(accessToken));
        System.out.println("After print the access token details");
        return ResponseEntity.ok().body(this.customerService.all());
    }
}
