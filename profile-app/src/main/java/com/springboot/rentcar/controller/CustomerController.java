package com.springboot.rentcar.controller;

import com.springboot.rentcar.common.model.Customer;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(value = "/byDlNumber")
    public ResponseEntity<Customer> findByDlNumber(@RequestParam("dlNumber") String dlNumber){
        return ResponseEntity.ok().body(this.customerService.findByDlNumber(dlNumber));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('read_profile')")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok().body(this.customerService.all());
    }
}
