package com.springboot.rentcar.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.springboot.rentcar.common.util.ConstantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

@Configuration
public class PostFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostFilter.class);
    @Override
    public String filterType() {
        return "post";
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
        HttpServletResponse servletResponse = requestContext.getResponse();
        String responseBody = responseBody(requestContext);
        LOGGER.info("{} -> {} -> {} -> {}", "API GATEWAY", ConstantValue.RESPONSE, servletResponse.getStatus(), responseBody);
        requestContext.setResponseBody(responseBody);
        return null;
    }

    private String responseBody(RequestContext requestContext) {
        String jsonFormatResponse = null;
        final InputStream responseDataStream = requestContext.getResponseDataStream();
        try {
            jsonFormatResponse = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            LOGGER.error("{} -> {}", "Http response conversion issue", e.getMessage());
        }
        return jsonFormatResponse;
    }
}
