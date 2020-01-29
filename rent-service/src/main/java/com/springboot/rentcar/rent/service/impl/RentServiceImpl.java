package com.springboot.rentcar.rent.service.impl;

import com.springboot.rentcar.common.model.rent.Rent;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.common.wrapper.rent.PartialRentResponse;
import com.springboot.rentcar.common.wrapper.rent.RentMap;
import com.springboot.rentcar.common.wrapper.rent.RentResponse;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.rent.client.CustomerClient;
import com.springboot.rentcar.rent.client.VehicleClient;
import com.springboot.rentcar.rent.enumeration.ResponseType;
import com.springboot.rentcar.rent.feign.CustomerFeignClient;
import com.springboot.rentcar.rent.feign.VehicleFeignClient;
import com.springboot.rentcar.rent.repository.RentRepository;
import com.springboot.rentcar.rent.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {
    private RentRepository rentRepository;
    private CustomerClient customerClient;
    private VehicleClient vehicleClient;
    private CustomerFeignClient customerFeignClient;
    private VehicleFeignClient vehicleFeignClient;

    @Override
    public PartialRentResponse create(Rent rent) {
        return buildPartialRentResponse(this.rentRepository.save(rent));
    }

    @Override
    public RentResponse findById(int id, ResponseType responseType) {
        Optional<Rent> response = this.rentRepository.findById(id);
        if(response.isPresent())  {
            if(ResponseType.FULL != responseType){
                return buildPartialRentResponse(response.get());
            }
            //using rest template
            /*else {
                CustomerResponse customerResponse = this.customerClient.getCustomerById(response.get().getCustomerId());
                VehicleResponse vehicleResponse = this.vehicleClient.getVehicleById(response.get().getVehicleId());
                return RentMap.mapFullRentResponse(response.get(), customerResponse, vehicleResponse);
            }*/
            //using feign client
             else {
                CustomerResponse customerResponse = this.customerFeignClient.findById(response.get().getCustomerId());
                VehicleResponse vehicleResponse = this.vehicleFeignClient.findById(response.get().getVehicleId());
                return RentMap.mapFullRentResponse(response.get(), customerResponse, vehicleResponse);
            }
        }

        return null;
    }

    @Override
    public List<PartialRentResponse> all() {
        return this.rentRepository
                .findAll()
                .stream()
                .map(this::buildPartialRentResponse)
                .collect(Collectors.toList());
    }

    private PartialRentResponse buildPartialRentResponse(Rent rent){
        return RentMap.mapPartialRentResponse(rent);
    }
}
