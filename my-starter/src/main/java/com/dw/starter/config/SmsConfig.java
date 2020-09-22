package com.dw.starter.config;

import com.dw.starter.properties.SmsProperties;
import com.dw.starter.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SmsProperties.class)
@ConditionalOnProperty(
        prefix = "sms",
        name = {"host", "key"}
)
public class SmsConfig {

    @Autowired
    private SmsProperties smsProperties;

    @Bean(name = "smsService")
    public SmsService smsService() {
        return new SmsService(smsProperties.getHost(), smsProperties.getKey());
    }

}
