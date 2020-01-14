package com.yuuki.cooky.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 一些方法
 */
public final class CookyUtils {

    public static String getIp(HttpServletRequest request){
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }



}
