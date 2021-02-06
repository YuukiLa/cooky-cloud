package com.yuuki.cooky.auth.controller;

import com.yuuki.cooky.auth.feign.UserService;
import com.yuuki.cooky.auth.service.ValidateCodeService;
import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.common.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    UserService userService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        validateCodeService.create(request, response);
    }


    @GetMapping("/user")
    public Principal user(Principal member) {
        //获取当前用户信息
        return member;
    }

    @GetMapping("/user/detail")
    public Response userDetail(Principal member) {
        UserVo userVo = userService.findByUserName(member.getName());
        userVo.setPassword("********");
        return Response.success(userVo);
    }

    @DeleteMapping(value = "/exit")
    public Object revokeToken(HttpServletRequest request,String access_token) {
        request.getParameter("Authoriation");
        //注销当前用户
        Map<String,Object> result = new HashMap<>();
        if (consumerTokenServices.revokeToken(access_token)) {
            result.put("code",200);
            result.put("msg","注销成功");
        } else {
            result.put("code",500);
            result.put("msg","注销失败");
        }
        return result;
    }


}
