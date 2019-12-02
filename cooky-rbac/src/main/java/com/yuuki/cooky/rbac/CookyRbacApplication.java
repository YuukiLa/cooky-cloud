package com.yuuki.cooky.rbac;

import com.yuuki.cooky.common.annotations.EnableRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedis
public class CookyRbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookyRbacApplication.class, args);
    }

    
}
