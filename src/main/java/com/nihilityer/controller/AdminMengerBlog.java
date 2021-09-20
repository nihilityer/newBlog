package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Blog;
import com.nihilityer.mapper.BlogMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminMengerBlog
 * @Description 管理博客页的controller。
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

    /**
     *
     * @return 增加1访问量后查找所有博客，并在页面输出，
     */
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
        //时间输出格式调整
        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mengerBlog.addObject("format", toStringFormat);

        mengerBlog.setViewName("admin/mBlog");
        return mengerBlog;
    }

    @GetMapping("/admin/delete/{blogId}")
    public void deleteById(@PathVariable("blogId") int blogId, HttpServletResponse response) throws IOException {

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("blog_id", blogId);
        int delete = blogMapper.delete(blogQueryWrapper);
        if (delete != 1) {
            throw new RuntimeException("删除失败！");
        }
        //删除博客后更新一下系统信息数据库
        boolean b = updateWebInfoService.reduceBlogNumberOne();
        if (!b) {
            throw new RuntimeException("更新失败！");
        }
        //使用跳转，而不是直接return一个页面数据组合来显示，不显示路径，防止刷现出错。
        response.sendRedirect("/admin/mBlog");
    }

}
