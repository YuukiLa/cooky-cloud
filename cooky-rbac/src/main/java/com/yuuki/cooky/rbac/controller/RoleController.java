package com.yuuki.cooky.rbac.controller;

import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Role;
import com.yuuki.cooky.rbac.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * (Role)表控制层
 *
 * @author yuuki
 * @since 2019-11-13 16:25:03
 */
@RestController
@RequestMapping("role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class RoleController  {
    
    private final RoleService roleService;

    /**
     * 分页查询所有数据
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/bulk")
    @PreAuthorize("hasAuthority('role:view')")
    public Page selectByPage(PageInfo params, Role role) {
        return (Page) this.roleService.selectWithMenu(params,role);
    }

    /**
     *  获取所有角色列表
     */
    @GetMapping
    public List<Role> selectAll() {
        return roleService.list();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Response selectOne(@PathVariable Serializable id) {
        return Response.success(this.roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果 success、 false
     */
    @PostMapping
    @PreAuthorize("hasAuthority('role:add')")
    public Response insert(@RequestBody @Valid Role role) {
        return roleService.addRole(role);
    }

    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果 success、 false
     */
    @PutMapping
    @PreAuthorize("hasAuthority('role:edit')")
    public Response update(@RequestBody @Valid Role role) {
        return this.roleService.updateRole(role);
    }

    /**
     * 删除数据
     *
     * @param ids 主键
     * @return 删除结果
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('role:del')")
    public Response deleteOne(@PathVariable String ids) {
        return this.roleService.deleteRoles(ids);
    }
}