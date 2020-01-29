package com.springboot.rentcar.common.wrapper.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String dlNumber;
    private String zipCode;
}
