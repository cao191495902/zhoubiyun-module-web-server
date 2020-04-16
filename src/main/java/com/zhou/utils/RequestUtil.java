package com.zhou.utils;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description:请求工具类
 * 
 * @author: YSL
 * @email: songlin.yang@downjoy.com
 * @create at: 2018年8月3日 下午1:47:46
 */
public class RequestUtil {
    private static boolean isWindows;

    static{
        String os = System.getProperty("os.name").toLowerCase();
        isWindows = os.contains("windows");
    }
    /**
     * 取参数值
     * 
     * @param request
     * @param paraName
     * @return
     */
    public static String getString(HttpServletRequest request, String paraName) {
        return request.getParameter(paraName);
    }

    /**
     * 判断是否为AJAX请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                || request.getParameter("ajax") != null;
    }

    public static String getUserIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.indexOf(",") != -1) {
            String[] array = ip.split(",");
            for (String str : array) {
                if (str.indexOf("127.0.0.1") == -1 && str.indexOf("192.168.") == -1) {
                    return str.trim();
                }
            }
        }
        return ip;
    }

    public static String getCookieValue(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getValue() != null && cookieName.equals(cookie.getName())) {
                    String value = "";
                    try {
                        value = URLDecoder.decode(cookie.getValue().trim(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                    if (value.indexOf("%") > -1) {
                        return null;
                    }
                    return value;
                }
            }
        }
        return null;
    }

    public static void setLoginCookiesParam(HttpServletRequest request, HttpServletResponse response, String key, String value, int cookiesAge) {
        if (value != null && value.length() != 0) {
            try {
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }

        String serverName = request.getServerName();
        String domain = request.getServerName();
        try {
            setCookiesParamWithEncodedValue(response, key, value, cookiesAge, domain);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 设置cookie, 值已经encode了
     * @param response
     * @param key 名称
     * @param value 值
     * @param cookiesAge 存活时间 单位/秒
     */
    public static void setCookiesParamWithEncodedValue(HttpServletResponse response, String key, String value, int cookiesAge, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(cookiesAge);
        cookie.setPath("/");
        if (domain != null) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    /**
     * 是否公司内部ip地址
     * @param requestIP
     * @return
     */
    public static boolean isInnerIp(String requestIP) {
        return requestIP.startsWith("192.168") || requestIP.startsWith("211.147.5.") || requestIP.startsWith("115.182.62") || requestIP.startsWith("115.182.198") ||
                requestIP.startsWith("115.182.49") || requestIP.startsWith("118.144.66") || requestIP.startsWith("118.144.65") || requestIP.startsWith("127.0.0");
    }



}
