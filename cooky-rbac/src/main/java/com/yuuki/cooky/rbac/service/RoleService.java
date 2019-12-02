package com.yuuki.cooky.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Role;

/**
 * (Role)表服务接口
 *
 * @author yuuki
 * @since 2019-11-13 16:25:03
 */
public interface RoleService extends IService<Role> {

    Page selectWithMenu(PageInfo params, Role role);

    Response addRole(Role role);

    Response updateRole(Role role);

    Response deleteRoles(String ids);
}