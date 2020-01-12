package com.springboot.rentcar.service.impl;

import com.springboot.rentcar.common.model.Customer;
import com.springboot.rentcar.repository.CustomerRepository;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer findByDlNumber(String dlNumber) {
        return this.customerRepository.findByDlNumber(dlNumber).orElse(null);
    }

    @Override
    public List<Customer> all() {
        return this.customerRepository.findAll();
    }
}
