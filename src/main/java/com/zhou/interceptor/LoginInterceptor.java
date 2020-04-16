package com.zhou.interceptor;


import com.zhou.utils.RequestUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 登录拦截器
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI = request.getRequestURI();// 本次请求路径

        if (URI.contains("/login")||URI.equals("/")) {
            return true;
        }
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if (!"1".equals(token)) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
