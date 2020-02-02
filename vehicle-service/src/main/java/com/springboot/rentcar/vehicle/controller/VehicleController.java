package com.springboot.rentcar.vehicle.controller;

import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleMap;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleRequest;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
@AllArgsConstructor

public class VehicleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> create(@RequestBody VehicleRequest vehicleRequest){
        return ResponseEntity.ok().body(this.vehicleService.create(VehicleMap.mapVehicleRequest(vehicleRequest)));
    }

    @GetMapping(value = "/byType")
    public ResponseEntity<VehicleResponse> findByType(@RequestParam("type") String type){
        return ResponseEntity.ok().body(this.vehicleService.findByType(type));
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<VehicleResponse> findById(@RequestParam("id") Integer id){
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.REQUEST, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), id);
        final VehicleResponse vehicleResponse = this.vehicleService.findById(id);
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.RESPONSE, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JacksonUtil.convertObjectToJson(vehicleResponse));
        return ResponseEntity.ok().body(vehicleResponse);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> findAll() {
        return ResponseEntity.ok().body(this.vehicleService.all());
    }
}
