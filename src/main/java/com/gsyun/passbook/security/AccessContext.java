package com.gsyun.passbook.security;

/**
 * @author gongshiyun
 * @Description 用ThreadLocal去单独存储每一个线程携带的Token信息
 * @date 2018/7/25
 */
public class AccessContext {

    private static final ThreadLocal<String> token = new ThreadLocal<String>();

    public static String getToken() {
        return token.get();
    }

    public static void setToken(String tokenStr) {
        token.set(tokenStr);
    }

    public static void clearAccessKey() {
        token.remove();
    }
}
