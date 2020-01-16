package com.springboot.rentcar.web.thirdparty;

import com.springboot.rentcar.common.model.Customer;
import com.springboot.rentcar.web.util.AccessTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProfileServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value(value = "${thirdparty.profile.service.endpoint}")
    private String url;

    public List<Customer> getAllCustomers(){
        HttpEntity<?> request = new HttpEntity<>(AccessTokenUtil.createAccessTokenHeader());
        try{
            ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, Customer[].class);
            return Arrays.asList(responseEntity.getBody());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
