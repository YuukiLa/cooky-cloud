package com.yuuki.cooky.common.annotations;


import com.yuuki.cooky.common.config.RedisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisConfiguration.class)
public @interface EnableRedis {
}
