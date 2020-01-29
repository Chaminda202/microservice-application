package com.springboot.rentcar.rent.service;

import com.springboot.rentcar.common.model.rent.Rent;
import com.springboot.rentcar.common.wrapper.rent.PartialRentResponse;
import com.springboot.rentcar.common.wrapper.rent.RentResponse;
import com.springboot.rentcar.rent.enumeration.ResponseType;

import java.util.List;

public interface RentService {
    PartialRentResponse create(Rent rent);
    RentResponse findById(int id, ResponseType responseType);
    List<PartialRentResponse> all();
}
