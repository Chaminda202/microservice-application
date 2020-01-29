package com.springboot.rentcar.rent.feign;

import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profile-service")
public interface CustomerFeignClient {
    @GetMapping(value = "/customers/byId")
    CustomerResponse findById(@RequestParam("id") Integer id);
}
