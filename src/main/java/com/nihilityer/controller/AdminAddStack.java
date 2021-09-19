package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.TStack;
import com.nihilityer.mapper.TStackMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminAddStack
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 15:21
 */
@RestController
public class AdminAddStack {

    private final UpdateWebInfoService updateWebInfoService;

    private final TStackMapper tStackMapper;

    public AdminAddStack(TStackMapper tStackMapper, UpdateWebInfoService updateWebInfoService) {
        this.tStackMapper = tStackMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/admin/addStack")
    public ModelAndView toNewStack() {
        ModelAndView addStack = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        QueryWrapper<TStack> tStackQueryWrapper = new QueryWrapper<>();
        List<TStack> tStacks = tStackMapper.selectList(tStackQueryWrapper);

        //读取放入二维数组，应该就可以输出了,在前端渲染的时候，格式刚好正确
        ArrayList<String[]> stackList = new ArrayList<>();
        for (TStack stackRow :
                tStacks) {
            String[] oneRow = new String[2];
            oneRow[0] = stackRow.getName();
            oneRow[1] = String.valueOf(stackRow.getGrade());
            stackList.add(oneRow);
        }
        addStack.addObject("stackList", stackList);

        addStack.setViewName("admin/addStack");
        return addStack;
    }

    @PostMapping("/admin/addStack")
    public ModelAndView addStack(@Param("title") String title, @Param("grade") int grade) {
        ModelAndView addStack = new ModelAndView();

        QueryWrapper<TStack> tStackQueryWrapper = new QueryWrapper<>();

        tStackQueryWrapper.eq("name", title);
        TStack tStack = tStackMapper.selectOne(tStackQueryWrapper);
        TStack newStack = new TStack(title, grade);
        if (tStack != null) {
            int update = tStackMapper.update(newStack, tStackQueryWrapper);
            if (update != 1) {
                throw new RuntimeException("更新失败");
            }
        } else {
            int insert = tStackMapper.insert(newStack);
            if (insert != 1) {
                throw new RuntimeException("更新失败");
            }
        }

        QueryWrapper<TStack> tStackQueryWrapper2 = new QueryWrapper<>();
        List<TStack> tStacks = tStackMapper.selectList(tStackQueryWrapper2);

        //读取放入二维数组，应该就可以输出了,在前端渲染的时候，格式刚好正确
        ArrayList<String[]> stackList = new ArrayList<>();
        for (TStack stackRow :
                tStacks) {
            String[] oneRow = new String[2];
            oneRow[0] = stackRow.getName();
            oneRow[1] = String.valueOf(stackRow.getGrade());
            stackList.add(oneRow);
        }
        addStack.addObject("stackList", stackList);

        addStack.setViewName("admin/addStack");
        return addStack;
    }

}
