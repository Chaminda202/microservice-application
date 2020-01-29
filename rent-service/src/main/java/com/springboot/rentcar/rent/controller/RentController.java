package com.springboot.rentcar.rent.controller;

import com.springboot.rentcar.common.wrapper.rent.PartialRentResponse;
import com.springboot.rentcar.common.wrapper.rent.RentMap;
import com.springboot.rentcar.common.wrapper.rent.RentRequest;
import com.springboot.rentcar.common.wrapper.rent.RentResponse;
import com.springboot.rentcar.rent.enumeration.ResponseType;
import com.springboot.rentcar.rent.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rents")
public class RentController {
    private RentService rentService;

    @PostMapping
    public ResponseEntity<PartialRentResponse> create(@RequestBody RentRequest rentRequest){
        return ResponseEntity.ok().body(this.rentService.create(RentMap.mapRentRequest(rentRequest)));
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<RentResponse> findById(@RequestParam("id") Integer id, @RequestParam(value = "type", required = false)ResponseType responseType){
        return ResponseEntity.ok().body(this.rentService.findById(id, responseType));
    }

    @GetMapping
    public ResponseEntity<List<PartialRentResponse>> findAll() {
        return ResponseEntity.ok().body(this.rentService.all());
    }
}
