package com.yuuki.cooky.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CookyAuthApplicationTests {



    @Test
    public void password() {
        System.out.println(new BCryptPasswordEncoder().encode(""));
    }

}
