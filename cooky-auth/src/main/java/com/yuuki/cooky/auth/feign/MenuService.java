package com.yuuki.cooky.auth.feign;

import com.yuuki.cooky.auth.feign.fallback.MenuServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "cooky-rbac",contextId = "menuServiceClient",fallbackFactory = MenuServiceFallback.class)
public interface MenuService {

    @GetMapping("/menu/userPerms/{username}")
    public String findPermsByUser(@PathVariable String username);

}
