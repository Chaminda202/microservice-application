package com.springboot.rentcar.vehicle.controller;

import com.springboot.rentcar.common.wrapper.vehicle.VehicleMap;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleRequest;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.vehicle.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
@AllArgsConstructor

public class VehicleController {
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
        return ResponseEntity.ok().body(this.vehicleService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> findAll() {
        return ResponseEntity.ok().body(this.vehicleService.all());
    }
}
