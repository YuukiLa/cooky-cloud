package com.yuuki.cooky.music;

import com.yuuki.cooky.common.annotations.EnableRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CookyMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookyMusicApplication.class, args);
    }

}
