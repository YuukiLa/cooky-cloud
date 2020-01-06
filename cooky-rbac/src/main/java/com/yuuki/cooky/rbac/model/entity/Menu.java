package com.yuuki.cooky.rbac.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * (Menu)表实体类
 *
 * @author makejava
 * @since 2019-09-08 19:51:58
 */
@Data
@TableName("t_menu")
@SuppressWarnings("serial")
public class Menu {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 父节点id
     */
    private Integer parentId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 组件名
     */
    private String menuComponent;

    private String menuPath;
    @NotBlank(message = "{required}")
    private String menuTitle;

    private String uri;

    private String method;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限
     */
    private String perms;
    /**
     * 类型（0：菜单，1：按钮）
     */
    @NotBlank(message = "{required}")
    private Integer type;
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