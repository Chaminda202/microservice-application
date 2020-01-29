package com.springboot.rentcar.repository;

import com.springboot.rentcar.common.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {
    Optional<Customer> findByDlNumber(String dlNumber);
}
