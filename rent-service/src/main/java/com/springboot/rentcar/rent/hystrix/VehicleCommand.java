package com.springboot.rentcar.rent.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.springboot.rentcar.common.wrapper.vehicle.VehicleResponse;
import com.springboot.rentcar.rent.client.VehicleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehicleCommand extends HystrixCommand <VehicleResponse> {
    Logger logger = LoggerFactory.getLogger(VehicleCommand.class);
    private VehicleClient vehicleClient;
    private Integer vehicleId;

    public VehicleCommand(VehicleClient vehicleClient, Integer vehicleId) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.vehicleClient = vehicleClient;
        this.vehicleId = vehicleId;
    }

    @Override
    protected VehicleResponse run() throws Exception {
        return this.vehicleClient.getVehicleById(this.vehicleId);
    }

    @Override
    protected VehicleResponse getFallback() {
        logger.info("Call the default method in getVehicleById....");
        return new VehicleResponse();
    }
}
