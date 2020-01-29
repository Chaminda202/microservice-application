package com.springboot.rentcar.common.wrapper.rent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartialRentResponse extends RentResponse{
    private Integer rentId;
    private Integer customerId;
    private Integer vehicleId;
    private String rentFrom;
    private String rentTill;
    private Integer currentMeter;
    private String returnLocation;
}
