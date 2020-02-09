package com.springboot.rentcar.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.springboot.rentcar.common.util.ConstantValue;
import com.springboot.rentcar.common.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class PreFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreFilter.class);
    @Override
    public String filterType() {
        return "pre";
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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = requestContext.getRequest();
        LOGGER.info("{} -> {} -> {} -> {} -> {} -> {}", "API GATEWAY", ConstantValue.REQUEST, servletRequest.getMethod(), requestBody(servletRequest),
                servletRequest.getRemoteAddr(), servletRequest.getRequestURI());;
        return null;
    }

    private String requestBody(HttpServletRequest request) {
        String jsonFormatRequest = null;
        if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod()) ){
            try {
                jsonFormatRequest = request.getReader().lines().collect(Collectors.joining());
            } catch (IOException e) {
                LOGGER.error("{} -> {}", "Http request conversion issue", e.getMessage());
            }
        }
        return jsonFormatRequest;
    }
}
