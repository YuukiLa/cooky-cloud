package com.yuuki.cooky.auth.properties;


import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:cooky-auth.properties"})
@ConfigurationProperties(prefix = "cooky.auth")
public class AuthProperties {


    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;


    private ValidateCodeProperties validateCodeProperties = new ValidateCodeProperties();




}
