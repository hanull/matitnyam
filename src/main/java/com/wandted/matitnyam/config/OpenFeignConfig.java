package com.wandted.matitnyam.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("com.wandted.matitnyam")
@Configuration
public class OpenFeignConfig {
}
