package com.yuuki.cooky.rbac.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * (Dept)表实体类
 *
 * @author makejava
 * @since 2019-11-13 11:13:25
 */
@Data
@TableName("t_dept")
@SuppressWarnings("serial")
public class Dept {

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
     * 部门名
     */
    @NotBlank(message = "{required}")
    private String deptName;
    /**
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    @TableField(value = "`describe`")
    private String describe;
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