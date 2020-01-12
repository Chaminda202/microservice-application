package com.springboot.rentcar.vehicle.controller;

import com.springboot.rentcar.common.model.Vehicle;
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
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle){
        return ResponseEntity.ok().body(this.vehicleService.create(vehicle));
    }

    @GetMapping(value = "/byType")
    public ResponseEntity<Vehicle> findByType(@RequestParam("type") String type){
        return ResponseEntity.ok().body(this.vehicleService.findByType(type));
    }

    @GetMapping(value = "/byType")
    public ResponseEntity<Vehicle> findById(@RequestParam("id") Integer id){
        return ResponseEntity.ok().body(this.vehicleService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll(){
        return ResponseEntity.ok().body(this.vehicleService.all());
    }
}
