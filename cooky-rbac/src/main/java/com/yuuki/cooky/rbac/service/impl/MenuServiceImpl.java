package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.util.Strings;
import com.yuuki.cooky.rbac.model.entity.Menu;
import com.yuuki.cooky.rbac.mapper.MenuMapper;
import com.yuuki.cooky.rbac.model.vo.VueRouter;
import com.yuuki.cooky.rbac.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Menu)表服务实现类
 *
 * @author makejava
 * @since 2019-09-08 19:51:58
 */
@Service("menuService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    final MenuMapper menuMapper;

    @Override
    public String findPermsByUser(String username) {
        return menuMapper.findPermsByUser(username)
                .stream()
                .map(Menu::getPerms)
                .collect(Collectors.joining(","));
    }

    @Override
    public Response findUserRouter(String username) {
//        List<Menu> permsByUser = menuMapper.findPermsByUser(username);
        List<Menu> userMenu = menuMapper.findUserMenu(username);
        String[] perms = Strings.split(this.findPermsByUser(username),",");//permsByUser.stream().map(Menu::getPerms).collect(Collectors.toList());
        List<VueRouter<Menu>> routes = new ArrayList<>();
        userMenu.forEach(menu -> {
            VueRouter<Menu> route = new VueRouter<>();
            route.setId(menu.getId());
            route.setParentId(menu.getParentId());
            route.setPath(menu.getMenuPath());
            route.setComponent(menu.getMenuComponent());
            route.setName(menu.getMenuName());
            route.setTitle(menu.getMenuTitle());
            route.setHidden(false);
            route.setIcon(menu.getIcon());

            routes.add(route);
        });
        return Response.success().put("perms",perms).put("menus",buildVueRouter(routes));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteMenus(String ids) {
        String[] strings = Strings.splitByWholeSeparatorPreserveAllTokens(ids, ",");
        this.update(new LambdaUpdateWrapper<Menu>()
                .in(Menu::getParentId,strings)
                .set(strings.length>0,Menu::getParentId,0));
        this.removeByIds(Arrays.asList(strings));
        return Response.success("删除成功");
    }

    private <T> List<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes) {
        if (routes == null) {
            return null;
        }
        List<VueRouter<T>> topRoutes = new ArrayList<>();
        VueRouter<T> router = new VueRouter<>();
        routes.forEach(route -> {
            Integer parentId = route.getParentId();
            if (parentId == null || parentId==0) {
                topRoutes.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                Integer id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null)
                        parent.initChildren();
                    parent.getChildren().add(route);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });
        VueRouter<T> router404 = new VueRouter<>();
        router404.setName("CommonNotfoundError");
        router404.setComponent("components/error-pages/404");
        router404.setHidden(true);
        router404.setTitle("页面找不到");
        router404.setPath("*");

        topRoutes.add(router404);
        return topRoutes;
    }

}