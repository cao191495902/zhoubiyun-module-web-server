package com.zhou.controller;


import com.zhou.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Value("${user_name}")
    private String userName;
    @Value("${password}")
    private String password;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("newIndex");
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, HttpServletRequest request) {

        String name = RequestUtil.getString(request, "name");
        String pword = RequestUtil.getString(request, "password");

        if (userName.equals(name) && password.equals(pword)) {
            RequestUtil.setLoginCookiesParam(request, response, "TOKEN_IN_COOKIE", "1", 3600 * 24);
            return "1";
        } else {
            return "-1";
        }
    }

    @RequestMapping("/show")
    public ModelAndView show(HttpServletRequest request) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if(!"1".equals(token)){
            return new ModelAndView("error");
        }

        return new ModelAndView("show");
    }
}
