package com.bxz.config;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author baixiangzhu
 * @date 2018/5/24
 **/
@Configuration
public class EndpointConfig {

    @Bean
    public Endpoint<Map<String, Object>> customEndPoint() {
        return new JstackEndPoint();
    }
}
