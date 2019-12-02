package com.yuuki.cooky.rbac.mapper;


import com.yuuki.cooky.rbac.model.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-08 19:51:58
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findPermsByUser(@Param("username") String username);
    List<Menu> findUserMenu(@Param("username") String username);

}