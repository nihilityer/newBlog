package com.nihilityer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName LoginController
 * @Description 登陆页跳转，无其他功能
 * @Author nihilityer
 * @Date 2021/9/8 15:01
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ModelAndView toLogin() {
        ModelAndView login = new ModelAndView();
        login.setViewName("login");
        return login;
    }

}
