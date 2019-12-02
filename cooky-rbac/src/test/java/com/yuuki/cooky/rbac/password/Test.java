package com.yuuki.cooky.rbac.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    @org.junit.Test
    public void encode(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("123456"));


    }


}
