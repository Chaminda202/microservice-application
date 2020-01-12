package com.springboot.rentcar.vehicle.service;

import com.springboot.rentcar.common.model.Vehicle;

import java.util.List;

public interface VehicleService {
    public Vehicle create(Vehicle vehicle);
    public Vehicle findByType(String type);
    public Vehicle findById(Integer id);
    public List<Vehicle> all();
}
