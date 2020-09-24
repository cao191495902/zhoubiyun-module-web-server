package com.zhou.controller;


import com.zhou.bean.LoveAgreeTo;
import com.zhou.bean.ThingRecordTo;
import com.zhou.bean.UserBaseInfo;
import com.zhou.service.UserService;
import com.zhou.utils.RSAUtil;
import com.zhou.utils.RequestUtil;
import com.zhou.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LoginController {


    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if ("1".equals(token)) {
            return new ModelAndView("show");
        } else {
            return new ModelAndView("newIndex");
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, HttpServletRequest request) {


        String name = RequestUtil.getString(request, "name");
        String pword = RequestUtil.getString(request, "password");

        UserBaseInfo userBaseInfo = null;
        Integer id = 0;
        try {
            id = Integer.parseInt(name);
        } catch (Exception e) {
        }
        if (id > 0) {
            userBaseInfo = userService.getUserInfoById(id);
        }

        if (userBaseInfo == null) {
            userBaseInfo = userService.getUserInfoByUserName(name);
        }
        if (userBaseInfo == null) {
            return "用户名不存在";
        }
        //解密密码
        pword = RSAUtil.decryptRequestParam(pword);

        if (userBaseInfo.getPassword().equalsIgnoreCase(pword)) {
            RequestUtil.setLoginCookiesParam(request, response, "TOKEN_IN_COOKIE", userBaseInfo.getId() + "", 3600 * 24 * 30);
            return "1";
        } else {
            return "密码错误";
        }
    }

    @RequestMapping("/show")
    public ModelAndView show(HttpServletRequest request) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if (StringUtil.isEmpty(token)) {
            return new ModelAndView("newIndex");
        }

        return new ModelAndView("show");
    }
    @RequestMapping("/xiaozhan")
    public ModelAndView xiaozhan(HttpServletRequest request) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if (StringUtil.isEmpty(token)) {
            return new ModelAndView("newIndex");
        }

        return new ModelAndView("xiaozhan");
    }

    @RequestMapping("/agree-index")
    public ModelAndView submit(HttpServletRequest request,Model model) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if (StringUtil.isEmpty(token)) {
            return new ModelAndView("newIndex");
        }
        Integer id = 0;
        try {
            id = Integer.parseInt(token);
        } catch (Exception e) {
        }
        List<LoveAgreeTo>  list  =  userService.getAgreeListById(id);
        model.addAttribute("list",list);

        return new ModelAndView("agreeIndex");
    }

    @RequestMapping("/thing-record")
    public ModelAndView record(HttpServletRequest request,Model model) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if (StringUtil.isEmpty(token)) {
            return new ModelAndView("newIndex");
        }
        Integer id = 0;
        try {
            id = Integer.parseInt(token);
        } catch (Exception e) {
        }
        List<ThingRecordTo>  list  =  userService.getRecordListById(id);
        model.addAttribute("list",list);

        return new ModelAndView("thingRecord");
    }

    @RequestMapping("/intro")
    public ModelAndView intro(HttpServletRequest request,Model model) {
        String token = RequestUtil.getCookieValue("TOKEN_IN_COOKIE", request);
        if (StringUtil.isEmpty(token)) {
            return new ModelAndView("newIndex");
        }
        Integer id = 0;
        try {
            id = Integer.parseInt(token);
        } catch (Exception e) {
        }
        UserBaseInfo user  =  userService.getUserInfoById(id);
        user.setPhone(StringUtil.replacePhone(user.getPhone()));
        model.addAttribute("user",user);

        return new ModelAndView("intro");
    }
}
