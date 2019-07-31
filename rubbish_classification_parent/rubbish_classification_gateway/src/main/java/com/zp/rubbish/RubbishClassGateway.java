package com.zp.rubbish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //zuul注解
@EnableDiscoveryClient
public class RubbishClassGateway {
    public static void main(String[] args) {
        SpringApplication.run(RubbishClassGateway.class, args);
    }
}
