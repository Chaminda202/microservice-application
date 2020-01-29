package com.springboot.rentcar.vehicle.service;

import com.springboot.rentcar.common.model.vehicle.Vehicle;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;

import java.util.List;

public interface VehicleService {
    public VehicleResponse create(Vehicle vehicle);
    public VehicleResponse findByType(String type);
    public VehicleResponse findById(Integer id);
    public List<VehicleResponse> all();
}
