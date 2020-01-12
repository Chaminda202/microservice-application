package com.springboot.rentcar.repository;

import com.springboot.rentcar.common.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {
}
