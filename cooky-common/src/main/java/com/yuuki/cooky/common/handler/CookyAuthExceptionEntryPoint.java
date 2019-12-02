package com.yuuki.cooky.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuuki.cooky.common.model.Response;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookyAuthExceptionEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest reuquest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JSONObject.toJSONString(Response.faild("token 无效")));
    }
}
