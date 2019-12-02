package com.yuuki.cooky.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    public static final int SUCCESS_CODE = 200;
    public static final int FAILD_CODE = 500;
    public static final int UNAUTH_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;

    private int code;

    private String msg;

    private Object data;


    public static Response success(String msg, Object data) {
        return new Response(SUCCESS_CODE, msg, data);
    }

    public static Response faild(String msg) {
        return new Response(FAILD_CODE, msg, null);
    }

    public static Response success() {
        return success("成功", null);
    }

    public static Response success(Object data) {
        return success("成功", data);
    }

    public static Response faild() {
        return faild("失败");
    }

    public static Response success(String msg) {
        return success(msg, null);
    }


    public Response put(String key,Object object){
        if (this.data==null) {
            this.data = new HashMap<>();
        }
        ((Map)this.data).put(key,object);
        return this;
    }

}
