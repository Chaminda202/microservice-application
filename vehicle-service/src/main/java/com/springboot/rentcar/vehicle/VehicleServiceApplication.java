package com.springboot.rentcar.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"com.springboot.rentcar.common.model.vehicle"})
@EnableEurekaClient
public class VehicleServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
	}

}
