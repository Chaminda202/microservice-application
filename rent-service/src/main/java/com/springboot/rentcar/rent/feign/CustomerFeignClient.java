package com.springboot.rentcar.rent.feign;

import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-service", url = "${customer.feign.client.url}")
public interface CustomerFeignClient {
    @GetMapping(value = "/byId")
    CustomerResponse findById(@RequestParam("id") Integer id);
}