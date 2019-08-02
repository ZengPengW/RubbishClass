package com.zp.rubbish.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class MySrpingMVCConfig extends WebMvcConfigurerAdapter {
    //自定义消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter=new StringHttpMessageConverter(Charset.forName("utf-8"));
        converters.add(converter);
    }
}
