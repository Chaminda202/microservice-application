package com.springboot.rentcar.service.impl;

import com.springboot.rentcar.common.model.Customer;
import com.springboot.rentcar.repository.CustomerRepository;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public Customer create(Customer customer){
        return this.customerRepository.save(customer);
    }
}
