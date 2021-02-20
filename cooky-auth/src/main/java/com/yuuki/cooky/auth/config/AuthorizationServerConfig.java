package com.yuuki.cooky.auth.config;

import com.yuuki.cooky.auth.error.MssWebResponseExceptionTranslator;
import com.yuuki.cooky.auth.feign.MenuService;
import com.yuuki.cooky.auth.feign.UserService;
import com.yuuki.cooky.auth.granter.CookyGranterCollection;
import com.yuuki.cooky.auth.service.RedisClientDetailsService;
import com.yuuki.starter.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.UUID;


@Configuration
@EnableAuthorizationServer
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MssWebResponseExceptionTranslator exceptionTranslator;
    @Autowired
    DataSource dataSource;
    @Autowired
    RedisClientDetailsService redisClientDetailsService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @Value("${cooky.jwt:false}")
    private boolean jwt;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public TokenStore tokenStore() {
        if(jwt){
            return new JwtTokenStore(accessTokenConverter());
        }
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
     * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
     * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 这个方法主要是用于校验注册的第三方客户端的信息，可以存储在数据库中，默认方式是存储在内存中，如下所示，注释掉的代码即为内存中存储的方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
         clients.withClientDetails(redisClientDetailsService);
    }
//    @Bean
//    @Primary
//    public ClientDetailsService clientDetails() {
//        RedisClientDetailsService redisClientDetailsService = new RedisClientDetailsService(dataSource);
//        redisClientDetailsService.loadAllClientToCache();
//        return redisClientDetailsService;
//    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenGranter tokenGranter = CookyGranterCollection.getTokenGranter(authenticationManager, endpoints, redisUtils, userService,menuService);
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(exceptionTranslator);
        endpoints.tokenGranter(tokenGranter);
        if(jwt){
            endpoints.accessTokenConverter(accessTokenConverter());
        }else {
            endpoints.tokenServices(defaultTokenServices());
        }
//


    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * 自定义的token
     * 认证的token是存到redis里的
     *
     * @return
     */
    @Primary
    @Bean
//    @ConditionalOnProperty(value = "cooky.jwt",havingValue = "true")
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();

        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(redisClientDetailsService);
        return tokenServices;
    }

    /**
     * Jwt资源令牌转换器<br>
     *
     * @return accessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("cooky.jks"), "cooky8".toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("cooky"));


        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailsService);

        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
//        jwtAccessTokenConverter.setSigningKey("cooky");

        return jwtAccessTokenConverter;
    }

}
