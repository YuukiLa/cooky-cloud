package com.yuuki.cooky.auth.feigin;

import com.yuuki.cooky.auth.feigin.fallback.UserServiceFallback;
import com.yuuki.cooky.common.model.UserVo;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "cooky-rbac",contextId = "userServiceClient",fallbackFactory = UserServiceFallback.class)
public interface UserService {


    @GetMapping("/user/findByUsername/{username}")
    UserVo findByUserName(@PathVariable String username);
}
