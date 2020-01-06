package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.common.model.PageInfo;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.model.UserVo;
import com.yuuki.cooky.common.util.Strings;
import com.yuuki.cooky.rbac.mapper.UserMapper;
import com.yuuki.cooky.rbac.model.entity.User;
import com.yuuki.cooky.rbac.model.entity.UserRole;
import com.yuuki.cooky.rbac.service.UserRoleService;
import com.yuuki.cooky.rbac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2019-08-28 21:04:38
 */
@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    final UserMapper userMapper;

    final UserRoleService userRoleService;



    @Override
    public UserVo findByUsername(String username) {
        return this.userMapper.findByUsername(username);
    }

    @Override
    public Page selectUserWithRoleAndDept(PageInfo params, User user) {
        return userMapper.selectUserWithRoleAndDept(new QueryRequest(params),user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response addUser(UserVo user) {
        UserVo byUsername = findByUsername(user.getUsername());
        if(byUsername!=null) {
            return Response.faild("用户名已存在");
        }
        User user1 = new User();
        BeanUtils.copyProperties(user,user1);
        user1.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.save(user1);
        user.setId(user1.getId());
        setUserRole(user);
        return Response.success("新增成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response editUser(UserVo user) {
        User user1 = new User();
        BeanUtils.copyProperties(user,user1);
        user1.setPassword(null);
        this.updateById(user1);
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().ge(UserRole::getUserId,user1.getId()));
        setUserRole(user);
        return Response.success("修改成功");
    }

    @Override
    public Response deleteUsers(String ids) {
        List<String> strings = Arrays.asList(Strings.split(ids, ","));
        this.removeByIds(strings);
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().in(UserRole::getUserId,strings));
        return Response.success("删除成功");
    }

    @Override
    public Response updateAvatar(String avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        this.update(new LambdaUpdateWrapper<User>()
                .eq(User::getUsername, username)
                .set(Strings.isNotEmpty(username),User::getAvatar,avatar));
        return Response.success("更新成功");
    }

    private void setUserRole(UserVo user) {
        List<UserRole> userRoles = user.getRoleIds().stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            return userRole;
        }).collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
    }
}