package com.springboot.rentcar.common.wrapper.rent;

import com.springboot.rentcar.common.formatter.DateFormatter;
import com.springboot.rentcar.common.model.rent.Rent;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;

public class RentMap {
    public static Rent mapRentRequest(RentRequest rentRequest){
        return new Rent(rentRequest.getCustomerId(), rentRequest.getVehicleId(),
                DateFormatter.convertStringToLocalDateTime(rentRequest.getRentTill()),
                rentRequest.getCurrentMeter(), rentRequest.getReturnLocation());
    }

    public static PartialRentResponse mapPartialRentResponse(Rent rent) {
        return PartialRentResponse.builder()
                .rentId(rent.getRentId())
                .customerId(rent.getCustomerId())
                .vehicleId(rent.getVehicleId())
                .rentFrom(DateFormatter.convertLocalDateTimeToString(rent.getRentFrom()))
                .rentTill(DateFormatter.convertLocalDateTimeToString(rent.getRentTill()))
                .currentMeter(rent.getCurrentMeter())
                .returnLocation(rent.getReturnLocation())
                .build();
    }

    public static FullRentResponse mapFullRentResponse(Rent rent, CustomerResponse customerResponse, VehicleResponse vehicleResponse) {
        return FullRentResponse.builder()
                .rentId(rent.getRentId())
                .customerResponse(customerResponse)
                .vehicleResponse(vehicleResponse)
                .rentFrom(DateFormatter.convertLocalDateTimeToString(rent.getRentFrom()))
                .rentTill(DateFormatter.convertLocalDateTimeToString(rent.getRentTill()))
                .currentMeter(rent.getCurrentMeter())
                .returnLocation(rent.getReturnLocation())
                .build();
    }

}

