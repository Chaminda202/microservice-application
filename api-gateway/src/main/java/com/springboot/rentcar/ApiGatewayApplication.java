package com.springboot.rentcar;

import com.springboot.rentcar.filter.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public PreFilter createPreFilter(){
		return new PreFilter();
	}
}
