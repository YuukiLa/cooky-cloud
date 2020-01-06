package com.yuuki.cooky.common.model;

import com.yuuki.cooky.common.util.Strings;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class UserVo implements Serializable {


    private static final long serialVersionUID = 3394752920021124415L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    @NotBlank(message = "{required}")
    private String username;
    /**
     * 密码
     */
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
    private String describe;
    /**
     * 状态 0：禁用 1：启用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date ct;
    /**
     * 修改时间
     */
    private Date mt;

    private String deptName;

    private String roleId;

    private List<Integer> roleIds;

    private String roleName;

//    private String perms;

    public List<Integer> getRoleIds() {
        if (Strings.isNotEmpty(roleId)) {
            return Arrays.stream(Strings.split(roleId,","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        if(CollectionUtils.isEmpty(this.roleIds)) {
            return new ArrayList<>();
        }else {
            return this.roleIds;
        }
    }
}
