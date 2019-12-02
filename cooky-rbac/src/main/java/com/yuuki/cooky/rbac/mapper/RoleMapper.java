package com.yuuki.cooky.rbac.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuuki.cooky.rbac.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (Role)表数据库访问层
 *
 * @author yuuki
 * @since 2019-11-13 16:25:03
 */
public interface RoleMapper extends BaseMapper<Role> {

    Page selectRoleWithMenu(Page page,Role role);

}