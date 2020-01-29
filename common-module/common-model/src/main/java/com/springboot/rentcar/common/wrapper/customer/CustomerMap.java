package com.springboot.rentcar.common.wrapper.customer;

import com.springboot.rentcar.common.model.customer.Customer;

public class CustomerMap {
    public static Customer mapCustomerRequest(CustomerRequest customerRequest){
        return new Customer(customerRequest.getFirstName(), customerRequest.getLastName(),
                customerRequest.getDlNumber(), customerRequest.getZipCode());
    }

    public static CustomerResponse mapCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dlNumber(customer.getDlNumber())
                .zipCode(customer.getZipCode())
                .build();
    }
}
