package com.zhou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:cookie工具类
 * 
 * @author: YSL
 * @email: songlin.yang@downjoy.com
 * @create at: 2018年8月5日 下午2:34:15
 */
public class CookieUtil {

    private static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * @Description: 获取cookie中的值
     * 
     * @Params: cookieName 指定cookie的名称
     * @return: String key为cookieName的value值
     * @author: YSL
     * @email: songlin.yang@downjoy.com
     * @create at: 2018年8月21日 上午9:57:36
     */
    public static String getCookieVal(HttpServletRequest request, String cookieName) {
        if (request != null && request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (StringUtil.decode(cookieName, "UTF-8").equals(StringUtil.decode(cookie.getName(), "UTF-8"))) {
                    return StringUtil.decode(cookie.getValue(), "UTF-8");
                }
            }
        }
        return null;
    }



}
