package com.yuuki.cooky.rbac.mapper;


import com.yuuki.cooky.rbac.model.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * (LoginLog)表数据库访问层
 *
 * @author yuuki
 * @since 2020-01-06 17:27:01
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    List<Map<String,Object>> countLoginNum();

}