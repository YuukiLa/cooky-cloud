package com.yuuki.cooky.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.rbac.model.entity.LoginLog;
import com.yuuki.cooky.rbac.mapper.LoginLogMapper;
import com.yuuki.cooky.rbac.service.LoginLogService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;
import sun.management.Agent;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * (LoginLog)表服务实现类
 *
 * @author yuuki
 * @since 2020-01-06 17:27:01
 */
@Service("loginLogService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Transient
    @Override
    public Response saveLoginLog(HttpServletRequest request) {
        LoginLog loginLog = new LoginLog();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loginLog.setUsername(username);
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        loginLog.setIp(details.getRemoteAddress());
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser(); //获取浏览器信息 
        OperatingSystem os = userAgent.getOperatingSystem(); //获取操作系统信息
        loginLog.setBorwser(browser.toString());
        loginLog.setSystem(os.toString());
        loginLog.setLoginTime(new Date());
        this.save(loginLog);
        return Response.success();
    }

    @Override
    public Response countLoginNum(){
        List<Map<String,Object>> result = loginLogMapper.countLoginNum();
        Map<String, List> data = new HashMap<>();
        data.put("count",new ArrayList<>());
        data.put("date",new ArrayList<>());
        result.forEach(map -> {
            data.get("count").add(map.get("num"));
            data.get("date").add(map.get("date"));
        });
        return Response.success(data);
    }

}