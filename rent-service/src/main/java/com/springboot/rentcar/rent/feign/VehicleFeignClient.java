package com.springboot.rentcar.rent.feign;

import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vehicle-service")
public interface VehicleFeignClient {
    @GetMapping(value = "/vehicles/byId")
    VehicleResponse findById(@RequestParam("id") Integer id);
}
