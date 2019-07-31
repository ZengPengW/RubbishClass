package com.zp.rubbish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //EurekaServer 服务端启动类，接受其他微服务注册进来
public class Eureka_App_02 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_App_02.class, args);
    }
}
