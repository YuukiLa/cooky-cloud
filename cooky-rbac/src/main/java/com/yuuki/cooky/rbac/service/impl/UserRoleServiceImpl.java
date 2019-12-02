package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.rbac.model.entity.UserRole;
import com.yuuki.cooky.rbac.mapper.UserRoleMapper;
import com.yuuki.cooky.rbac.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author yuuki
 * @since 2019-11-24 21:22:01
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}