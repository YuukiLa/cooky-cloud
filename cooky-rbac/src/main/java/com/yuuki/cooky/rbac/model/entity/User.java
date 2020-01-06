package com.yuuki.cooky.rbac.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2019-08-28 21:04:37
 */
@Data
@TableName("t_user")
@SuppressWarnings("serial")
public class User {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @TableField("`password`")
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别 0：男 1：女 2：保密
     */
    private String sex;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;
    /**
     * 状态 0：禁用 1：启用
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date ct;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date mt;


}