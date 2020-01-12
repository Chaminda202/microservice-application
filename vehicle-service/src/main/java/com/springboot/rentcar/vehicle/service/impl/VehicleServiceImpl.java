package com.springboot.rentcar.vehicle.service.impl;

import com.springboot.rentcar.common.model.Vehicle;
import com.springboot.rentcar.vehicle.repository.VehicleRepository;
import com.springboot.rentcar.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle findByType(String type) {
        return this.vehicleRepository.findByType(type).orElse(null);
    }

    @Override
    public Vehicle findById(Integer id) {
        return this.vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> all() {
        return this.vehicleRepository.findAll();
    }
}
