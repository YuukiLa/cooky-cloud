package com.yuuki.cooky.rbac.controller;

import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Menu;
import com.yuuki.cooky.rbac.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2019-09-08 19:51:58
 */
@RestController
@RequestMapping("menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class MenuController  {
    
    private final MenuService menuService;

    /**
     * 分页查询所有数据
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/bulk")
    @PreAuthorize("hasAuthority('menu:view')")
    public List<Menu> selectAll(PageInfo params, Menu menu) {
        return this.menuService.list();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Response selectOne(@PathVariable Serializable id) {
        return Response.success(this.menuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param menu 实体对象
     * @return 新增结果 success、 false
     */
    @PostMapping
    @PreAuthorize("hasAuthority('menu:add')")
    public Response insert(@RequestBody @Valid Menu menu) {
        return Response.success(this.menuService.save(menu));
    }

    /**
     * 修改数据
     *
     * @param menu 实体对象
     * @return 修改结果 success、 false
     */
    @PutMapping
    @PreAuthorize("hasAuthority('menu:edit')")
    public Response update(@RequestBody @Valid Menu menu) {
        return Response.success(this.menuService.updateById(menu));
    }

    /**
     * 删除数据
     *
     * @param ids 主键
     * @return 删除结果
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('menu:del')")
    public Response deleteOne(@PathVariable String ids) {
        return menuService.deleteMenus(ids);
    }

    @GetMapping("userPerms/{username}")
    public String findPermsByUser(@PathVariable String username){
        return menuService.findPermsByUser(username);
    }

    @GetMapping("/userRouter/{username}")
    public Response findUserRouter(@PathVariable String username) {
        return menuService.findUserRouter(username);
    }
}