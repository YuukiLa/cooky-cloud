package com.yuuki.cooky.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class CookyMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookyMonitorAdminApplication.class, args);
    }

}
