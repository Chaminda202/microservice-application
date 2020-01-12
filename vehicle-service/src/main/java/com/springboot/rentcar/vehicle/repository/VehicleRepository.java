package com.springboot.rentcar.vehicle.repository;

import com.springboot.rentcar.common.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository <Vehicle, Integer> {
    Optional<Vehicle> findByType(String type);
}
