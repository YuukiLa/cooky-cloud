package com.yuuki.cooky.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.LoginLog;

import javax.servlet.http.HttpServletRequest;

/**
 * (LoginLog)表服务接口
 *
 * @author yuuki
 * @since 2020-01-06 17:27:01
 */
public interface LoginLogService extends IService<LoginLog> {

    Response saveLoginLog(HttpServletRequest request);

    Response countLoginNum();
}