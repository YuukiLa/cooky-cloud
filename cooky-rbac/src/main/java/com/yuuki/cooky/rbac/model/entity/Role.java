package com.yuuki.cooky.rbac.model.entity;

import java.util.Arrays;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yuuki.cooky.common.util.Strings;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Role)表实体类
 *
 * @author yuuki
 * @since 2019-11-13 16:25:03
 */
@Data
@TableName("t_role")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
public class Role {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名
     */
    @NotBlank(message = "{required}")
    private String roleName;
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

    @TableField(exist = false)
    private String menuIds;


    public List<Integer> getMenuIds() {
        if (Strings.isNotEmpty(this.menuIds)) {
            return Arrays.stream(Strings.split(this.menuIds, ","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        return null;
    }


}