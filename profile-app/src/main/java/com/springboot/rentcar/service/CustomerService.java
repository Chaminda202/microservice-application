package com.springboot.rentcar.service;

import com.springboot.rentcar.common.model.customer.Customer;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;

import java.util.List;

public interface CustomerService {
    public CustomerResponse create(Customer customer);
    public CustomerResponse findByDlNumber(String dlNumber);
    public CustomerResponse findById(Integer id);
    public List<CustomerResponse> all();
}
