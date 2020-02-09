package com.springboot.rentcar.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ZuulProxyConfig {
    @Autowired
    ZuulProperties properties;

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        SwaggerResourcesProvider swaggerResourcesProvider = () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            properties.getRoutes().values().stream()
                    .forEach(route -> resources.add(createResource(route.getServiceId(), route.getId(), "1.0")));
            /*resources.add(createResource("vehicle-service", "/vehicle/v2/api-docs?group=SpringBoot Rest Service", "2.0"));
            resources.add(createResource("rent-service", "/rent/v2/api-docs?group=SpringBoot Rest Service", "2.0"));
            resources.add(createResource("profile-service", "/profile/v2/api-docs?group=SpringBoot Rest Service", "2.0"));*/
            return resources;
        };
        return swaggerResourcesProvider;
    }

    private SwaggerResource createResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        /*swaggerResource.setLocation(location);
        swaggerResource.setLocation("/" + location + "/v2/api-docs?group=SpringBoot Rest Service");*/
        swaggerResource.setLocation("/" + location + "/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
