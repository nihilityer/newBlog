package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Blog;
import com.nihilityer.mapper.BlogMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminMengerBlog
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 15:12
 */
@RestController
public class AdminMengerBlog {

    private final UpdateWebInfoService updateWebInfoService;

    private final BlogMapper blogMapper;

    public AdminMengerBlog(BlogMapper blogMapper, UpdateWebInfoService updateWebInfoService) {
        this.blogMapper = blogMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/admin/mBlog")
    public ModelAndView toMengerBlog() {
        ModelAndView mengerBlog = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.groupBy("blog_id");
        List<Blog> blogs = blogMapper.selectList(blogQueryWrapper);

        mengerBlog.addObject("blogList",blogs);

        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mengerBlog.addObject("format", toStringFormat);

        mengerBlog.setViewName("admin/mBlog");
        return mengerBlog;
    }

    @GetMapping("/admin/delete/{blogId}")
    public ModelAndView deleteById(@PathVariable("blogId") int blogId) {
        ModelAndView mengerBlog = new ModelAndView();

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("blog_id", blogId);
        int delete = blogMapper.delete(blogQueryWrapper);
        if (delete != 1) {
            throw new RuntimeException("删除失败！");
        }

        boolean b = updateWebInfoService.reduceBlogNumberOne();
        if (!b) {
            throw new RuntimeException("更新失败！");
        }

        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("blog_id");
        List<Blog> blogs = blogMapper.selectList(queryWrapper);

        mengerBlog.addObject("blogList",blogs);

        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mengerBlog.addObject("format", toStringFormat);

        mengerBlog.setViewName("admin/mBlog");
        return mengerBlog;
    }

}
