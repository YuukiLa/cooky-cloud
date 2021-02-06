package com.yuuki.cooky.rbac.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuuki.cooky.common.model.UserVo;
import com.yuuki.cooky.rbac.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2019-08-28 21:04:38
 */
public interface UserMapper extends BaseMapper<User> {


    UserVo findByUsername(@Param("username")String username);
    UserVo findByPhone(@Param("phone")String phone);

    Page selectUserWithRoleAndDept(Page page,User user);
}