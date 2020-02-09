package com.springboot.rentcar.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //should be log relevant error trace
        RequestContext requestContext = RequestContext.getCurrentContext();
        Throwable throwable = RequestContext.getCurrentContext ().getThrowable();
        String errorResponse = requestContext.getResponseBody();
        LOGGER.error("{} -> {}", "API GATEWAY", throwable.getStackTrace());
        return null;
    }
}
