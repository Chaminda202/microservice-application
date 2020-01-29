package com.springboot.rentcar.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.springboot.rentcar.common.model.vehicle"})
public class VehicleServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
	}

}
