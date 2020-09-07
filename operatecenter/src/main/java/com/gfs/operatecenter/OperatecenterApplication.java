package com.gfs.operatecenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@MapperScan(basePackages = {"com.gfs.operatecenter.dao"})
public class OperatecenterApplication {

    @Value("${spring.profiles.active}")
    String profile;

    public static void main(String[] args) {
        SpringApplication.run(OperatecenterApplication.class, args);
    }

    @GetMapping("/health")
    public String getHealth(@RequestParam String value) {
        if ("k8s".equals(value)) {
            log.info("服务环境: {}", profile);
            return "succee";
        }
        return "error";
    }

}
