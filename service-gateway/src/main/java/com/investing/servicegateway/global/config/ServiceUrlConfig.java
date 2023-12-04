package com.investing.servicegateway.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "service-url")
public class ServiceUrlConfig {

    private String userServiceUrl;
    private String stockServiceUrl;

}
