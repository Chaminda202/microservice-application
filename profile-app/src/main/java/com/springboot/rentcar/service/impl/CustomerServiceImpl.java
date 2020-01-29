package com.springboot.rentcar.service.impl;

import com.springboot.rentcar.common.model.customer.Customer;
import com.springboot.rentcar.common.wrapper.customer.CustomerMap;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.repository.CustomerRepository;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(Customer customer) {
        return CustomerMap.mapCustomerResponse(this.customerRepository.save(customer));
    }

    @Override
    public CustomerResponse findByDlNumber(String dlNumber) {
        Optional<Customer> response = this.customerRepository.findByDlNumber(dlNumber);
        if (response.isPresent()){
            return  CustomerMap.mapCustomerResponse(response.get());
        }
        return null;
    }

    @Override
    public List<CustomerResponse> all() {
        return this.customerRepository
                .findAll()
                .stream()
                .map(this::buildCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse findById(Integer id) {
        Optional<Customer> response = this.customerRepository.findById(id);
        if (response.isPresent()){
            return  CustomerMap.mapCustomerResponse(response.get());
        }
        return null;
    }

    private CustomerResponse buildCustomerResponse(Customer customer){
        return CustomerMap.mapCustomerResponse(customer);
    }
}
