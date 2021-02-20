package com.yuuki.cooky.auth.granter;

import com.yuuki.cooky.auth.feign.MenuService;
import com.yuuki.cooky.auth.feign.UserService;
import com.yuuki.cooky.auth.model.UserDetailImpl;
import com.yuuki.cooky.common.model.UserVo;
import com.yuuki.cooky.common.util.Strings;
import com.yuuki.starter.redis.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;
import java.util.Objects;

public class SmsTokenGranter extends AbstractTokenGranter {

    private AuthenticationManager authenticationManager;

    private RedisUtils redisUtils;

    private UserService userService;

    private MenuService menuService;

    public SmsTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType, RedisUtils redisUtils, UserService userService,MenuService menuService) {
        this(tokenServices, clientDetailsService, requestFactory, grantType);
        this.redisUtils = redisUtils;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.menuService = menuService;
    }

    protected SmsTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> requestParameters = tokenRequest.getRequestParameters();
        //获取手机号和验证码
        String phone = requestParameters.get("phone");
        String code = requestParameters.get("code");
        if (Strings.isAllBlank(phone,code)) {
            throw new InvalidGrantException("登录参数错误");
        }
        String redisCode = (String) redisUtils.get(phone);
        //// TODO: 2021/2/6 判断验证码,这边假装111111
        if (!Strings.equals(code,"111111")) {
            throw new InvalidGrantException("验证码错误");
        }
        UserVo userVo = userService.findByPhone(phone);
        if (Objects.isNull(userVo)) {
            throw new InvalidGrantException("不存在该手机号");
        }
        String perms = menuService.findPermsByUser(userVo.getUsername());
        UserDetailImpl userDetail = new UserDetailImpl(userVo.getId(), userVo.getUsername(), userVo.getPassword(), userVo.getStatus(), perms);
        BeanUtils.copyProperties(userVo,userDetail);
        Authentication userAuth = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
//        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
