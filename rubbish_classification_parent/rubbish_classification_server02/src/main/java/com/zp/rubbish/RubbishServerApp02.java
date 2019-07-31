package com.zp.rubbish;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@PropertySource(value={"classpath:properties/resources.properties"},ignoreResourceNotFound=true)
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中 只能用于euerka 注册中心
@EnableCircuitBreaker //对hystrix熔断机制的支持
public class RubbishServerApp02 extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //定义一个信息转换对象
        FastJsonHttpMessageConverter fastJsonConverter = new 		FastJsonHttpMessageConverter();
        //定义一个配置信息  比如要返回的json
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        //添加信息转换对象
        converters.add(fastJsonConverter);

    }

    public static void main(String[] args){
        SpringApplication.run(RubbishServerApp02.class,args);
    }
}
