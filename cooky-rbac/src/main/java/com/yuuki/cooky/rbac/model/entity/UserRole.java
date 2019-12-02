package com.yuuki.cooky.rbac.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;

/**
 * (UserRole)表实体类
 *
 * @author yuuki
 * @since 2019-11-24 21:22:01
 */
@Data
@TableName("t_user_role")
@SuppressWarnings("serial")
public class UserRole  {

                private Integer userId;
                    private Integer roleId;
    


}