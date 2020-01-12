package com.springboot.rentcar.service;

import com.springboot.rentcar.common.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer create(Customer customer);
    public Customer findByDlNumber(String dlNumber);
    public List<Customer> all();
}
