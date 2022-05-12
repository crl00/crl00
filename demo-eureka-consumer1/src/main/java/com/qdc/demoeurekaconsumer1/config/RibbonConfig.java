package com.qdc.demoeurekaconsumer1.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //配置类
public class RibbonConfig {

    @Bean
    @LoadBalanced  //负载均衡
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();

    }
}
