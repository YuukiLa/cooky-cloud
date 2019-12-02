package com.yuuki.cooky.auth.feigin.fallback;

import com.yuuki.cooky.auth.feigin.UserService;
import com.yuuki.cooky.common.model.UserVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceFallback implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public UserVo findByUserName(String username) {
                log.error("通过用户名查询用户发生异常：{}",throwable.getMessage());
                return null;
            }
        };
    }
}
