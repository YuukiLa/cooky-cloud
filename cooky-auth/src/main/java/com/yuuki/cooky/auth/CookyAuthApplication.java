package com.yuuki.cooky.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.yuuki.cooky.auth.feign"})
public class CookyAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookyAuthApplication.class, args);
    }

}
