package com.nihilityer.controller;

import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminIndexController
 * @Description 管理后台的首页，显示已有博客数、页面访问数、此页面访问不增加访问量
 * @Author nihilityer
 * @Date 2021/9/8 15:02
 */
@RestController
public class AdminIndexController {

    private final UpdateWebInfoService updateWebInfoService;

    public AdminIndexController(UpdateWebInfoService updateWebInfoService) {
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/admin/")
    public ModelAndView toAdmin() {
        ModelAndView admin = new ModelAndView();

        int visitsNumber = updateWebInfoService.getVisitsNumber();
        admin.addObject("visitsNumber", visitsNumber);

        int blogNumber = updateWebInfoService.getBlogNumber();
        admin.addObject("blogNumber", blogNumber);

        admin.setViewName("admin/index");
        return admin;
    }

}
