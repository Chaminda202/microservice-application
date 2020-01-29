package com.springboot.rentcar.controller;

import com.springboot.rentcar.common.wrapper.customer.CustomerMap;
import com.springboot.rentcar.common.wrapper.customer.CustomerRequest;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok().body(this.customerService.create(CustomerMap.mapCustomerRequest(customerRequest)));
    }

    @GetMapping(value = "/{dlNumber}")
    public ResponseEntity<CustomerResponse> findByDlNumber(@PathVariable("dlNumber") String dlNumber){
        return ResponseEntity.ok().body(this.customerService.findByDlNumber(dlNumber));
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<CustomerResponse> findById(@RequestParam("id") Integer id){
        return ResponseEntity.ok().body(this.customerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok().body(this.customerService.all());
    }
}
