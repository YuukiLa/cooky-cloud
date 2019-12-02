package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.rbac.model.entity.RoleMenu;
import com.yuuki.cooky.rbac.mapper.RoleMenuMapper;
import com.yuuki.cooky.rbac.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * (RoleMenu)表服务实现类
 *
 * @author yuuki
 * @since 2019-11-24 20:28:13
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}