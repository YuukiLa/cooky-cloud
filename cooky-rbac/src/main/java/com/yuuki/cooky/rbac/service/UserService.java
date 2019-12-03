package com.yuuki.cooky.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.model.UserVo;
import com.yuuki.cooky.rbac.model.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2019-08-28 21:04:38
 */
public interface UserService extends IService<User> {

    UserVo findByUsername(String username);


    Page selectUserWithRoleAndDept(PageInfo params, User user);

    Response addUser(UserVo user);

    Response editUser(UserVo user);

    Response deleteUsers(String ids);

    Response updateAvatar(String avatar);
}