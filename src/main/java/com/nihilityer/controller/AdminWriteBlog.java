package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Blog;
import com.nihilityer.mapper.BlogMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminWriteBlog
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 15:15
 */
@RestController
public class AdminWriteBlog {

    private final BlogMapper blogMapper;

    private final UpdateWebInfoService updateWebInfoService;

    public AdminWriteBlog(UpdateWebInfoService updateWebInfoService, BlogMapper blogMapper) {
        this.updateWebInfoService = updateWebInfoService;
        this.blogMapper = blogMapper;
    }

    @GetMapping("/admin/writeBlog")
    public ModelAndView toWriteBlog() {
        ModelAndView writeBlog = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }
        Blog blog = new Blog();
        writeBlog.addObject("blog", blog);

        writeBlog.setViewName("admin/newBlog");
        return writeBlog;
    }

    @PostMapping("/admin/writeBlog")
    public void writeBlog(@Param("title")String title, @Param("textBody")String textBody, HttpServletResponse response) throws IOException {

        Blog blog = new Blog();
        blog.setBlogTitle(title);
        blog.setText(textBody);
        blog.setCreater("Nihilityer");
        blog.setCreaterId(1000000001);
        blog.setCreateTime(new Date());
        int insert = blogMapper.insert(blog);
        if (insert != 1) {
            throw new RuntimeException("存储失败！");
        }

        response.sendRedirect("/admin/mBlog");
    }

    @GetMapping("/admin/writeBlog/{blogId}")
    public ModelAndView modifyBlog(@PathVariable("blogId") int blogId) {
        ModelAndView writeBlog = new ModelAndView();

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("blog_id", blogId);
        Blog blog = blogMapper.selectOne(blogQueryWrapper);
        writeBlog.addObject("blog", blog);

        writeBlog.setViewName("admin/newBlog");
        return writeBlog;
    }

}
