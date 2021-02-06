package com.yuuki.cooky.auth.feign;

import com.yuuki.cooky.auth.feign.fallback.UserServiceFallback;
import com.yuuki.cooky.common.model.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "cooky-rbac",contextId = "userServiceClient",fallbackFactory = UserServiceFallback.class)
public interface UserService {


    @GetMapping("/user/findByUsername/{username}")
    UserVo findByUserName(@PathVariable String username);

    @GetMapping("/user/findByPhone/{phone}")
    UserVo findByPhone(@PathVariable String phone);
}
