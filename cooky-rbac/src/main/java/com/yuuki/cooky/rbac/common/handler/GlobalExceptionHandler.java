package com.yuuki.cooky.rbac.common.handler;

import com.yuuki.cooky.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {


    /**
     * 异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return Response.faild("系统内部异常");
    }

    /**
     * 处理没权限
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response handleAccessDeniedException(){
        log.error("没有权限");
        return Response.builder()
                .code(Response.FORBIDDEN_CODE)
                .msg("没有权限访问该资源")
                .build();
    }

}
