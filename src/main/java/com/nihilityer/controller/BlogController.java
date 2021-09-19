package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Blog;
import com.nihilityer.mapper.BlogMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName BlogController
 * @Description 关于博客列表页的controller
 * @Author nihilityer
 * @Date 2021/9/8 14:46
 */
@RestController
public class BlogController {

    private final UpdateWebInfoService updateWebInfoService;

    private final BlogMapper blogMapper;

    public BlogController(BlogMapper blogMapper, UpdateWebInfoService updateWebInfoService) {
        this.blogMapper = blogMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/blog")
    public ModelAndView toBlog() {
        ModelAndView blog = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.groupBy("blog_id");
        List<Blog> blogs = blogMapper.selectList(blogQueryWrapper);
        blog.addObject("blogList",blogs);

        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        blog.addObject("format", toStringFormat);

        blog.setViewName("bolg");
        return blog;
    }

}
