package com.yuuki.cooky.auth.granter;

import com.yuuki.cooky.common.constant.CommonConstant;
import com.yuuki.cooky.common.exception.CookyException;
import com.yuuki.cooky.common.util.RedisUtils;
import com.yuuki.cooky.common.util.Strings;
import lombok.SneakyThrows;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 验证码登录
 * @author yuuki
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {

    private AuthenticationManager authenticationManager;

    private RedisUtils redisUtils;

    public CaptchaTokenGranter(AuthenticationManager authenticationManager,AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType,RedisUtils redisUtils) {
        this(tokenServices, clientDetailsService, requestFactory, grantType);
        this.redisUtils = redisUtils;
        this.authenticationManager = authenticationManager;

    }

    protected CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @SneakyThrows
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> requestParameters = tokenRequest.getRequestParameters();
        String username = requestParameters.get("username");
        String password = requestParameters.get("password");
        String code = requestParameters.get("code");
        String key = requestParameters.get("key");
        //判断验证码
        if(Strings.isAllBlank(key,code)) {
            throw new InvalidGrantException("验证码不能为空");
        }
        String redisCode = (String) redisUtils.get(CommonConstant.CODE_KEY_PREFIX + key);
        if(!Strings.equalsIgnoreCase(code,redisCode)) {
            throw new InvalidGrantException("验证码错误");
        }
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(requestParameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        }
        catch (AccountStatusException | BadCredentialsException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        // If the username/password are wrong the spec says we should send 400/invalid grant

        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
