package com.nihilityer.controller;

import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName IndexController
 * @Description 主页跳转controller，除了添加一次访问量不增加其他的
 * @Author nihilityer
 * @Date 2021/9/8 10:12
 */
@RestController()
public class IndexController {

    private final UpdateWebInfoService updateWebInfoService;

    public IndexController(UpdateWebInfoService updateWebInfoService) {
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        modelAndView.setViewName("index");
        return modelAndView;
    }

}
