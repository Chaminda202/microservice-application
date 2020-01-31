package com.springboot.rentcar.rent.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.springboot.rentcar.common.wrapper.customer.CustomerResponse;
import com.springboot.rentcar.rent.client.CustomerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerCommand extends HystrixCommand <CustomerResponse> {
    Logger logger = LoggerFactory.getLogger(CustomerCommand.class);
    private CustomerClient customerClient;
    private Integer customerId;

    public CustomerCommand(CustomerClient customerClient, Integer customerId) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.customerClient = customerClient;
        this.customerId = customerId;
    }

    @Override
    protected CustomerResponse run() throws Exception {
        return this.customerClient.getCustomerById(this.customerId);
    }

    @Override
    protected CustomerResponse getFallback() {
        logger.info("Call the default method in getCustomerById....");
        return new CustomerResponse();
    }
}
