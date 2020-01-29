package com.springboot.rentcar.vehicle.service.impl;

import com.springboot.rentcar.common.model.vehicle.Vehicle;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleMap;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.vehicle.repository.VehicleRepository;
import com.springboot.rentcar.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleResponse create(Vehicle vehicle) {
        return buildVehicleResponse(this.vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleResponse findByType(String type) {
        Optional<Vehicle> response = this.vehicleRepository.findByType(type);
        if(response.isPresent()){
            return buildVehicleResponse(response.get());
        }
        return null;
    }

    @Override
    public VehicleResponse findById(Integer id) {
        Optional<Vehicle> response = this.vehicleRepository.findById(id);
        if(response.isPresent()){
            return buildVehicleResponse(response.get());
        }
        return null;
    }

    @Override
    public List<VehicleResponse> all() {
        return this.vehicleRepository
                .findAll()
                .stream()
                .map(this::buildVehicleResponse)
                .collect(Collectors.toList());
    }

    private VehicleResponse buildVehicleResponse(Vehicle vehicle){
        return VehicleMap.mapCustomerResponse(vehicle);
    }
}
