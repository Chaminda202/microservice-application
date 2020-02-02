package com.springboot.rentcar.vehicle.service.impl;

import com.springboot.rentcar.common.model.vehicle.Vehicle;
import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleMap;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.vehicle.repository.VehicleRepository;
import com.springboot.rentcar.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);
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
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.REQUEST, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), id);
        Optional<Vehicle> response = this.vehicleRepository.findById(id);
        VehicleResponse vehicleResponse = null;
        if(response.isPresent()){
            vehicleResponse = buildVehicleResponse(response.get());
        }
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.RESPONSE, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JacksonUtil.convertObjectToJson(vehicleResponse));
        return vehicleResponse;
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
