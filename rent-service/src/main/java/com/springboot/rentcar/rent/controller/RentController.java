package com.springboot.rentcar.rent.controller;

import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import com.springboot.rentcar.common.wrapper.rent.PartialRentResponse;
import com.springboot.rentcar.common.wrapper.rent.RentMap;
import com.springboot.rentcar.common.wrapper.rent.RentRequest;
import com.springboot.rentcar.common.wrapper.rent.RentResponse;
import com.springboot.rentcar.rent.enumeration.ResponseType;
import com.springboot.rentcar.rent.service.RentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rents")
public class RentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);
    private RentService rentService;

    @PostMapping
    public ResponseEntity<PartialRentResponse> create(@RequestBody RentRequest rentRequest){
        return ResponseEntity.ok().body(this.rentService.create(RentMap.mapRentRequest(rentRequest)));
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<RentResponse> findById(@RequestParam("id") Integer id, @RequestParam(value = "type", required = false)ResponseType responseType){
        LOGGER.info("{} -> {} -> {} -> {} -> {}", ConstantValue.REQUEST, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), id, responseType);
        RentResponse rentResponse = this.rentService.findById(id, responseType);
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.RESPONSE, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JacksonUtil.convertObjectToJson(rentResponse));
        return ResponseEntity.ok().body(rentResponse);
    }

    @GetMapping
    public ResponseEntity<List<PartialRentResponse>> findAll() {
        return ResponseEntity.ok().body(this.rentService.all());
    }
}
