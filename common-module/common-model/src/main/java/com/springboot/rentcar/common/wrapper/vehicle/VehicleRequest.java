package com.springboot.rentcar.common.wrapper.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
    private String make;
    private String model;
    private String type;
    private Integer year;
}
