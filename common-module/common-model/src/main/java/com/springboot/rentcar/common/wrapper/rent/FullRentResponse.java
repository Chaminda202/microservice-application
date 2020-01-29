package com.springboot.rentcar.common.wrapper.rent;

import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullRentResponse extends RentResponse{
    private Integer rentId;
    private CustomerResponse customerResponse;
    private VehicleResponse vehicleResponse;
    private String rentFrom;
    private String rentTill;
    private Integer currentMeter;
    private String returnLocation;
}
