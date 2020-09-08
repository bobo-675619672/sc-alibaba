package com.ls.uc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.gitee.sunchenbin.mybatis.actable.manager.*","com.gitee.sunchenbin.mybatis.actable.manager.*","com.ls.uc.dao",})
public class UsercenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsercenterApplication.class, args);
    }

}
