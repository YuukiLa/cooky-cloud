package com.yuuki.cooky.rbac.controller;

import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Dept;
import com.yuuki.cooky.rbac.model.entity.Dept;
import com.yuuki.cooky.rbac.service.DeptService;
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
import java.util.Map;

/**
 * (Dept)表控制层
 *
 * @author yuuki
 * @since 2019-11-13 11:13:25
 */
@RestController
@RequestMapping("dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class DeptController  {
    
    private final DeptService deptService;

    /**
     * 分页查询所有数据
     *
     * @return 分页对象
     */
    @GetMapping("/bulk")
    @PreAuthorize("hasAuthority('dept:view')")
    public List<Dept> selectAll() {
        return this.deptService.list();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Response selectOne(@PathVariable Serializable id) {
        return Response.success(this.deptService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dept 实体对象
     * @return 新增结果 success、 false
     */
    @PostMapping
    @PreAuthorize("hasAuthority('dept:add')")
    public Response insert(@RequestBody @Valid Dept dept) {
        return Response.success(this.deptService.save(dept));
    }

    /**
     * 修改数据
     *
     * @param dept 实体对象
     * @return 修改结果 success、 false
     */
    @PutMapping
    @PreAuthorize("hasAuthority('dept:edit')")
    public Response update(@RequestBody @Valid Dept dept) {
        return Response.success(this.deptService.updateById(dept));
    }

    /**
     * 删除数据
     *
     * @param ids 主键
     * @return 删除结果
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('dept:del')")
    public Response delete(@PathVariable String ids) {
        return this.deptService.deleteDepts(ids);
    }
}