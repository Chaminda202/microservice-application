package com.springboot.rentcar.common.wrapper.vehicle;


import com.springboot.rentcar.common.model.vehicle.Vehicle;

public class VehicleMap {
    public static Vehicle mapVehicleRequest(VehicleRequest vehicleRequest){
        return new Vehicle(vehicleRequest.getMake(), vehicleRequest.getModel(),
                vehicleRequest.getType(), vehicleRequest.getYear());
    }

    public static VehicleResponse mapCustomerResponse(Vehicle vehicle){
        return VehicleResponse.builder()
                .vehicleId(vehicle.getId())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .type(vehicle.getType())
                .year(vehicle.getYear())
                .build();
    }
}
