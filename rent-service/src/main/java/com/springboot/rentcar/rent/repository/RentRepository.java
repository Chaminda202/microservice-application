package com.springboot.rentcar.rent.repository;

import com.springboot.rentcar.common.model.rent.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Integer> {
}
