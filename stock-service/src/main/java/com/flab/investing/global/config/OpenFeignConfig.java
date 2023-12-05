package com.flab.investing.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.flab.investing")
public class OpenFeignConfig {
}
