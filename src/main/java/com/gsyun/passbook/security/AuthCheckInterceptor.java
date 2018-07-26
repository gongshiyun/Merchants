package com.gsyun.passbook.security;

import com.gsyun.passbook.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gongshiyun
 * @Description 权限拦截器
 * @date 2018/7/25
 */
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.TOKEN_STRING);
        if (StringUtils.isEmpty(token)) {
            throw new Exception("Header中缺少" + Constants.TOKEN_STRING + "!");
        }

        if (!token.equals(Constants.TOKEN)) {
            throw new Exception("Header中" + Constants.TOKEN_STRING + "错误");
        }

        AccessContext.setToken(token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccessContext.clearAccessKey();
    }
}
