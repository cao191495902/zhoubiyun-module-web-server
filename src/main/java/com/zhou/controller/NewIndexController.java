package com.zhou.controller;


import com.zhou.service.UserService;
import com.zhou.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NewIndexController {


    @Autowired
    UserService userService;

    @RequestMapping("/timeToSay")
    public ModelAndView index(HttpServletRequest request) {

        System.out.println("ip = " +RequestUtil.getUserIpAddr(request));

        return new ModelAndView("newIndex2");

    }
}
