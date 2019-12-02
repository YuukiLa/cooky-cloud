package com.yuuki.cooky.auth.error;

import com.yuuki.cooky.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("exceptionTranslator")
public class MssWebResponseExceptionTranslator implements WebResponseExceptionTranslator {


    @Override
    public ResponseEntity translate(Exception e) {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.OK);
        Response response = new Response();
        response.setCode(Response.FAILD_CODE);
        String message = "认证失败";
        log.error(message, e);
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持该认证类型";
            response.setMsg(message);
            return status.body(response);
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效";
                response.setMsg(message);
                return status.body(response);
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                message = "用户已被锁定，请联系管理员";
                response.setMsg(message);
                return status.body(response);
            }
            message = "用户名或密码错误";
            response.setMsg(message);
            return status.body(response);
        }
        response.setMsg(message);
        return status.body(response);
    }
}
