package com.ls.uc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"com.gitee.sunchenbin.mybatis.actable.manager.*","com.gitee.sunchenbin.mybatis.actable.manager.*","com.ls.uc.dao","com.dw.*"})
public class UsercenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsercenterApplication.class, args);
    }

}
