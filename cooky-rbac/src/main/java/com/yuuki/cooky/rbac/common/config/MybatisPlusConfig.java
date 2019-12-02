package com.yuuki.cooky.rbac.common.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yuuki.cooky.common.handler.DateAutoGenHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yuuki.cooky.rbac.mapper")
public class MybatisPlusConfig {


    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 自动填充
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new DateAutoGenHandler();
    }


}
