package com.yuuki.cooky.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.Menu;

import java.io.Serializable;

/**
 * (Menu)表服务接口
 *
 * @author makejava
 * @since 2019-09-08 19:51:58
 */
public interface MenuService extends IService<Menu> {

    String findPermsByUser(String username);

    Response findUserRouter(String username);

    Response deleteMenus(String ids);
}