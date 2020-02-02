package com.springboot.rentcar.controller;

import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import com.springboot.rentcar.common.wrapper.customer.CustomerMap;
import com.springboot.rentcar.common.wrapper.customer.CustomerRequest;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@AllArgsConstructor
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
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
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.REQUEST, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), id);
        final CustomerResponse customerResponse = this.customerService.findById(id);
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.RESPONSE, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JacksonUtil.convertObjectToJson(customerResponse));
        return ResponseEntity.ok().body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok().body(this.customerService.all());
    }
}
