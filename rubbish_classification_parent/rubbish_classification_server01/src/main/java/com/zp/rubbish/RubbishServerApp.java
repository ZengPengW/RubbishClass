package com.zp.rubbish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"classpath:properties/resources.properties"},ignoreResourceNotFound=true)
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中 只能用于euerka 注册中心
@EnableCircuitBreaker //对hystrix熔断机制的支持
public class RubbishServerApp  {



    public static void main(String[] args){
        SpringApplication.run(RubbishServerApp.class,args);
    }

//    @Override//为了打包springboot项目
//    protected SpringApplicationBuilder configure(
//            SpringApplicationBuilder builder) {
//        return builder.sources(this.getClass());
//    }
}
