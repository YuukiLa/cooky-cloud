package com.yuuki.cooky.rbac.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;

/**
 * (RoleMenu)表实体类
 *
 * @author yuuki
 * @since 2019-11-24 20:28:13
 */
@Data
@TableName("t_role_menu")
@SuppressWarnings("serial")
public class RoleMenu  {

                private Integer roleId;
                    private Integer menuId;
    


}