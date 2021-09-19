package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.TStack;
import com.nihilityer.mapper.TStackMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName StackController
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 14:54
 */
@RestController
public class StackController {

    private final UpdateWebInfoService updateWebInfoService;

    private final TStackMapper tStackMapper;

    public StackController(TStackMapper tStackMapper, UpdateWebInfoService updateWebInfoService) {
        this.tStackMapper = tStackMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/stack")
    public ModelAndView toStack() {
        ModelAndView technologyStack = new ModelAndView();

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
        technologyStack.addObject("stackList", stackList);

        technologyStack.setViewName("technologyStack");
        return technologyStack;
    }

}
