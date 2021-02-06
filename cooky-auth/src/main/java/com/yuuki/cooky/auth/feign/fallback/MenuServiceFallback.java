package com.yuuki.cooky.auth.feign.fallback;

import com.yuuki.cooky.auth.feign.MenuService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MenuServiceFallback implements FallbackFactory<MenuService> {
    @Override
    public MenuService create(Throwable throwable) {
        return new MenuService() {
            @Override
            public String findPermsByUser(String username) {
                log.error("获取用户权限失败：{}",throwable.getMessage());
                return null;
            }
        };
    }
}
