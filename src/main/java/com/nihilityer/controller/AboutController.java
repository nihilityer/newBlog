package com.nihilityer.controller;

import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AboutController
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 14:56
 */
@RestController
public class AboutController {

    private final UpdateWebInfoService updateWebInfoService;

    public AboutController(UpdateWebInfoService updateWebInfoService) {
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/about")
    public ModelAndView toAbout() {
        ModelAndView about = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        about.setViewName("about");
        return about;
    }
}
