package com.springboot.rentcar.rent.client;

import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.rent.util.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomerClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value(value = "${profile.service.context.path}")
    private String contextPath;
    @Value(value = "${profile.get.by.id.endpoint}")
    private String getById;

    public CustomerResponse getCustomerById(Integer id){
        HttpEntity<?> request = new HttpEntity<>(AccessTokenUtil.createTokenHeader());
        String changeUrl = UriComponentsBuilder
                .fromUriString(this.buildFullUrl(this.contextPath, this.getById))
                .queryParam("id", id)
                .build()
                .toUri()
                .toString();
        ResponseEntity<CustomerResponse> responseEntity = restTemplate.exchange(changeUrl, HttpMethod.GET, request, CustomerResponse.class);
        return responseEntity.getBody();
    }

    private String buildFullUrl(String contextPath, String endpoint){
      StringBuilder builder = new StringBuilder();
      builder.append(contextPath);
      builder.append(endpoint);
      return builder.toString();
    }
}
