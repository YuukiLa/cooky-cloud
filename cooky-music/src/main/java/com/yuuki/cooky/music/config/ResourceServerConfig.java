package com.yuuki.cooky.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .requestMatchers().antMatchers("/**")
//                .and()
//                .authorizeRequests()
////                .antMatchers(anonUrls).permitAll()
//                .antMatchers("/**").authenticated()
//                .and().httpBasic();
        // 开发阶段先都放开
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests().anyRequest().permitAll();
    }


}
