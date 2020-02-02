package com.springboot.rentcar.service.impl;

import com.springboot.rentcar.common.model.customer.Customer;
import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import com.springboot.rentcar.common.wrapper.customer.CustomerMap;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.repository.CustomerRepository;
import com.springboot.rentcar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
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
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.REQUEST, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), id);
        Optional<Customer> response = this.customerRepository.findById(id);
        CustomerResponse customerResponse = null;
        if (response.isPresent()){
            customerResponse = CustomerMap.mapCustomerResponse(response.get());
        }
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.RESPONSE, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JacksonUtil.convertObjectToJson(customerResponse));
        return customerResponse;
    }

    private CustomerResponse buildCustomerResponse(Customer customer){
        return CustomerMap.mapCustomerResponse(customer);
    }
}
