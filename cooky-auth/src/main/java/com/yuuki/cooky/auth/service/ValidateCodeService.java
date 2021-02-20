package com.yuuki.cooky.auth.service;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yuuki.cooky.auth.properties.AuthProperties;
import com.yuuki.cooky.auth.properties.ValidateCodeProperties;
import com.yuuki.cooky.common.constant.CommonConstant;

import com.yuuki.cooky.common.util.Strings;
import com.yuuki.starter.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ValidateCodeService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AuthProperties properties;

    /**
     * 生成验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        String key = request.getParameter("key");
        if (Strings.isBlank(key)) {
            throw new Exception("验证码key不能为空");
        }
        ValidateCodeProperties code = properties.getValidateCodeProperties();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisUtils.set(CommonConstant.CODE_KEY_PREFIX + key, Strings.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     */
    public void check(String key, String value) throws Exception {
        Object codeInRedis = redisUtils.get(CommonConstant.CODE_KEY_PREFIX + key);
        if (Strings.isBlank(value)) {
            throw new Exception("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new Exception("验证码已过期");
        }
        if (!Strings.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new Exception("验证码不正确");
        }
    }

    private Captcha createCaptcha(ValidateCodeProperties code) {
        Captcha captcha = null;
        if (Strings.equalsIgnoreCase(code.getType(), "gif")) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (Strings.equalsIgnoreCase(type, "gif")) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
    
}
