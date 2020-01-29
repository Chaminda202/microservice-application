package com.springboot.rentcar.common.wrapper.rent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentRequest {
    Integer rentId;
    Integer customerId;
    Integer vehicleId;
    String rentTill;
    Integer currentMeter;
    String returnLocation;
}
