package com.yuuki.cooky.rbac.controller;

import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.model.UserVo;
import com.yuuki.cooky.rbac.model.entity.User;
import com.yuuki.cooky.rbac.service.LoginLogService;
import com.yuuki.cooky.rbac.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2019-08-28 21:04:38
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class UserController  {
    /**
     * 服务对象
     */
    private final UserService userService;
    private final LoginLogService loginLogService;

    /**
     * 分页查询所有数据
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @PreAuthorize("hasAuthority('user:view')")
    @GetMapping("/bulk")
    public Page selectAll(PageInfo params, User user) {
        return this.userService.selectUserWithRoleAndDept(params, user);
    }

    @PostMapping("/success")
    public Response loginSuccess(HttpServletRequest request) {
        return loginLogService.saveLoginLog(request);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:one')")
    public Response selectOne(@PathVariable Serializable id) {
        return Response.success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果 success、 false
     */
    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    public Response insert(@RequestBody @Valid UserVo user) {
        return userService.addUser(user);
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果 success、 false
     */
    @PutMapping
    @PreAuthorize("hasAuthority('user:edit')")
    public Response update(@RequestBody  @Valid UserVo user) {
        return userService.editUser(user);
    }

    /**
     * 更新头像
     * @param avatar
     * @return
     */
    @PutMapping("/avatar/{avatar}")
    public Response updateAvatar(@PathVariable String avatar) {
        return userService.updateAvatar(avatar);
    }
    /**
     * 删除数据
     *
     * @param ids 主键
     * @return 删除结果
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('user:del')")
    public Response deleteOne(@PathVariable String  ids) {
        
        return userService.deleteUsers(ids);
    }


    @GetMapping("findByUsername/{username}")
    public UserVo findByUserName(@PathVariable String username){
        return this.userService.findByUsername(username);
    }
}