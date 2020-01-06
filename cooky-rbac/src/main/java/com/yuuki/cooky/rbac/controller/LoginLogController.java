package com.yuuki.cooky.rbac.controller;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.LoginLog;
import com.yuuki.cooky.rbac.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Map;

/**
 * (LoginLog)表控制层
 *
 * @author yuuki
 * @since 2020-01-06 17:27:01
 */
@RestController
@RequestMapping("loginLog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginLogController  {
    
    private final LoginLogService loginLogService;

    /**
     * 分页查询所有数据
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/bulk")
    public Page selectAll(PageInfo params, LoginLog loginLog) {
        return (Page) this.loginLogService.page(new QueryRequest<>(params), new QueryWrapper<>(loginLog));
    }

    @GetMapping("/countLoginNum")
    public Response countLoginNum() {
        return loginLogService.countLoginNum();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Response selectOne(@PathVariable Serializable id) {
        return Response.success(this.loginLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param loginLog 实体对象
     * @return 新增结果 success、 false
     */
    @PostMapping
    public Response insert(@RequestBody LoginLog loginLog) {
        return Response.success(this.loginLogService.save(loginLog));
    }

    /**
     * 修改数据
     *
     * @param loginLog 实体对象
     * @return 修改结果 success、 false
     */
    @PutMapping
    public Response update(@RequestBody LoginLog loginLog) {
        return Response.success(this.loginLogService.updateById(loginLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Response deleteOne(@PathVariable Serializable id) {
        return Response.success(this.loginLogService.removeById(id));
    }
}