package com.yuuki.cooky.rbac.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * (LoginLog)表实体类
 *
 * @author yuuki
 * @since 2020-01-06 17:27:01
 */
@Data
@TableName("t_login_log")
@SuppressWarnings("serial")
public class LoginLog {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录ip
     */
    private String ip;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 登录系统
     */
    private String system;
    /**
     * 浏览器
     */
    private String borwser;


}