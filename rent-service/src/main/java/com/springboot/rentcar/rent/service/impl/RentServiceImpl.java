package com.springboot.rentcar.rent.service.impl;

import com.springboot.rentcar.common.model.rent.Rent;
import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.common.wrapper.rent.FullRentResponse;
import com.springboot.rentcar.common.wrapper.rent.PartialRentResponse;
import com.springboot.rentcar.common.wrapper.rent.RentMap;
import com.springboot.rentcar.common.wrapper.rent.RentResponse;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.rent.client.CustomerClient;
import com.springboot.rentcar.rent.client.VehicleClient;
import com.springboot.rentcar.rent.enumeration.ResponseType;
import com.springboot.rentcar.rent.feign.CustomerFeignClient;
import com.springboot.rentcar.rent.feign.VehicleFeignClient;
import com.springboot.rentcar.rent.hystrix.CommonHystrixCommand;
import com.springboot.rentcar.rent.repository.RentRepository;
import com.springboot.rentcar.rent.service.RentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentServiceImpl.class);
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
        LOGGER.info("{} -> {} -> {} -> {} -> {}", ConstantValue.REQUEST, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), id, responseType);
        RentResponse rentResponse = null;
        Optional<Rent> response = this.rentRepository.findById(id);
        if(response.isPresent())  {
            if(ResponseType.FULL != responseType){
                rentResponse = buildPartialRentResponse(response.get());
            }
            //using rest template with Hystrix
            else {
                CustomerResponse customerResponse = this.getCustomerResponse(response.get().getCustomerId());
                VehicleResponse vehicleResponse = this.getVehicleResponse(response.get().getVehicleId());
                rentResponse = RentMap.mapFullRentResponse(response.get(), customerResponse, vehicleResponse);
            }
            //using feign client
            /* else {
                CustomerResponse customerResponse = this.customerFeignClient.findById(response.get().getCustomerId());
                VehicleResponse vehicleResponse = this.vehicleFeignClient.findById(response.get().getVehicleId());
                return RentMap.mapFullRentResponse(response.get(), customerResponse, vehicleResponse);
            }*/
        }
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.RESPONSE, this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JacksonUtil.convertObjectToJson(rentResponse));
        return rentResponse;
    }

    @Override
    public List<PartialRentResponse> all() {
        return this.rentRepository
                .findAll()
                .stream()
                .map(this::buildPartialRentResponse)
                .collect(Collectors.toList());
    }

    //using separate Hystrix command implementation with rest template, feign implementation also like this
    /*private VehicleResponse getVehicleResponse(Integer vehicleId){
        VehicleCommand vehicleCommand = new VehicleCommand(this.vehicleClient, vehicleId);
        return vehicleCommand.execute();
    }

    private CustomerResponse getCustomerResponse(Integer customerId){
        CustomerCommand customerCommand = new CustomerCommand(this.customerClient, customerId);
        return customerCommand.execute();
    }*/

    //using common Hystrix command implementation
    private VehicleResponse getVehicleResponse(Integer vehicleId){
        CommonHystrixCommand<VehicleResponse> commonHystrixCommand = new CommonHystrixCommand<VehicleResponse>("default",
                ()-> {
                    return this.vehicleClient.getVehicleById(vehicleId);
                 },
                ()-> {
                    return new VehicleResponse();
                });
        return  commonHystrixCommand.execute();
    }

    private CustomerResponse getCustomerResponse(Integer customerId){
        CommonHystrixCommand<CustomerResponse> commonHystrixCommand = new CommonHystrixCommand<CustomerResponse>("default",
                ()-> {
                    return this.customerClient.getCustomerById(customerId);
                },
                ()-> {
                    return new CustomerResponse();
                });
        return  commonHystrixCommand.execute();
    }

    private PartialRentResponse buildPartialRentResponse(Rent rent){
        return RentMap.mapPartialRentResponse(rent);
    }
}
