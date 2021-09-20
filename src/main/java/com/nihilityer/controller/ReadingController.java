package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Blog;
import com.nihilityer.mapper.BlogMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName ReadingController
 * @Description 阅读页controller
 * @Author nihilityer
 * @Date 2021/9/8 14:58
 */
@RestController
public class ReadingController {

    private final UpdateWebInfoService updateWebInfoService;

    private final BlogMapper blogMapper;

    public ReadingController(BlogMapper blogMapper, UpdateWebInfoService updateWebInfoService) {
        this.blogMapper = blogMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    /**
     *
     * @param blogId 博客id
     * @return ModelAndView 其中包含reading的页面地址，以及页面中需要含有的博客标题，博客创建者id以及用户名，还有博客创建时间，在主体处输出博客内容
     *
     * 第一版完成，简单页面显示
     * 修改第一次： 创建时间输出优化
     */
    @GetMapping("/reading/{blogId}")
    public ModelAndView toReading(@PathVariable("blogId") String blogId) {
        ModelAndView reading = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("blog_id", blogId);
        Blog blog = blogMapper.selectOne(blogQueryWrapper);

        Date createTime = blog.getCreateTime();
        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd");

        reading.addObject("title", blog.getBlogTitle());
        reading.addObject("creater", blog.getCreater());
        reading.addObject("createId", blog.getCreaterId());
        reading.addObject("createTime", "创建时间："+toStringFormat.format(createTime));
        reading.addObject("textMain", blog.getText());

        reading.setViewName("reading");
        return reading;
    }

}
