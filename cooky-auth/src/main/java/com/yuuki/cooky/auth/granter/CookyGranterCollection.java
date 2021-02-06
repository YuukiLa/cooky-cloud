package com.yuuki.cooky.auth.granter;

import com.yuuki.cooky.auth.feign.MenuService;
import com.yuuki.cooky.auth.feign.UserService;
import com.yuuki.cooky.common.util.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CookyGranterCollection {

    /**
     * 自定义tokenGranter
     */
    public static TokenGranter getTokenGranter(final AuthenticationManager authenticationManager, final AuthorizationServerEndpointsConfigurer endpoints, RedisUtils redisUtils, UserService userService, MenuService menuService) {
        // 默认tokenGranter集合
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        // 增加验证码模式
        granters.add(new CaptchaTokenGranter(authenticationManager,endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), "captcha",redisUtils));
        // 短信验证码登录模式
        granters.add(new SmsTokenGranter(authenticationManager,endpoints.getTokenServices(),endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), "sms",redisUtils,userService,menuService));
        // 组合tokenGranter集合
        return new CompositeTokenGranter(granters);
    }

}
