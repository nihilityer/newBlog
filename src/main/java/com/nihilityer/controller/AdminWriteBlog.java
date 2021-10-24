package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Blog;
import com.nihilityer.entity.Users;
import com.nihilityer.mapper.BlogMapper;
import com.nihilityer.mapper.UsersMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @Description 写博客页的controller，其实都是新建一个，只不过修改是在已有内容上修改，方便快捷
 * @Author nihilityer
 * @Date 2021/9/8 15:15
 */
@RestController
public class AdminWriteBlog {

    private final BlogMapper blogMapper;

    private final UsersMapper usersMapper;

    private final UpdateWebInfoService updateWebInfoService;

    public AdminWriteBlog(UpdateWebInfoService updateWebInfoService, BlogMapper blogMapper, UsersMapper usersMapper) {
        this.updateWebInfoService = updateWebInfoService;
        this.blogMapper = blogMapper;
        this.usersMapper = usersMapper;
    }

    /**
     *  添加一个空的实体是因为前端页面有对blog的取值，不提供空值会出错
     * @return 跳转到页面
     */
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

    /**
     *
     * @param title 博客标题
     * @param textBody 博客正文，使用的是markdown存储
     * @param response 页面跳转所需参数
     * @throws IOException 页面跳转抛出的异常
     */
    @PostMapping("/admin/writeBlog")
    public void writeBlog(@Param("title")String title, @Param("textBody")String textBody, HttpServletResponse response) throws IOException {

        boolean b = updateWebInfoService.addBlogNumberOne();
        if (!b) {
            throw new RuntimeException("数据库更新失败");
        }

        Blog blog = new Blog();
        blog.setBlogTitle(title);
        blog.setText(textBody);

        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        usersQueryWrapper.eq("user_name", authentication.getName());
        Users users = usersMapper.selectOne(usersQueryWrapper);

        blog.setCreater(users.getUserName());
        blog.setCreaterId(users.getUserId());
        blog.setCreateTime(new Date());
//        System.out.println("blog = " + blog);
        blogMapper.insert(blog);

        response.sendRedirect("/admin/mBlog");
    }

    /**
     *
     * @param blogId 路径访问值，对应相应的博客id
     * @return 将博客内容取出后传值并输出，方便修改
     */
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
