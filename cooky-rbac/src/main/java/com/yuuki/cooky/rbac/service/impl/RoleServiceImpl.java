package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.util.Strings;
import com.yuuki.cooky.rbac.mapper.RoleMapper;
import com.yuuki.cooky.rbac.model.entity.Role;
import com.yuuki.cooky.rbac.model.entity.RoleMenu;
import com.yuuki.cooky.rbac.model.entity.UserRole;
import com.yuuki.cooky.rbac.service.RoleMenuService;
import com.yuuki.cooky.rbac.service.RoleService;
import com.yuuki.cooky.rbac.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Role)表服务实现类
 *
 * @author yuuki
 * @since 2019-11-13 16:25:03
 */
@Service("roleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;

    @Override
    public Page selectWithMenu(PageInfo params, Role role) {

        return this.baseMapper.selectRoleWithMenu(new QueryRequest<>(params),role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response addRole(Role role) {
        this.save(role);
        this.setRoleMenu(role);
        return Response.success("新增成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateRole(Role role) {
        this.updateById(role);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().ge(RoleMenu::getRoleId,role.getId()));
        this.setRoleMenu(role);
        return Response.success("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteRoles(String ids) {
        List<String> strings = Arrays.asList(Strings.split(ids, ","));
        this.removeByIds(strings);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getRoleId,strings));
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().in(UserRole::getRoleId,strings));
        return Response.success("删除成功");
    }

    private void setRoleMenu(Role role) {
        List<RoleMenu> roleMenus = role.getMenuIds().stream().map(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(menuId);
            return roleMenu;
        }).collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenus);
    }
}