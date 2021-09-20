package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Timeline;
import com.nihilityer.mapper.TimelineMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminNewTimeLine
 * @Description 添加时间线页的controller，至于添加，没有删除，相当于日记吧
 * @Author nihilityer
 * @Date 2021/9/8 15:19
 */
@RestController
public class AdminNewTimeLine {

    private final UpdateWebInfoService updateWebInfoService;

    private final TimelineMapper timelineMapper;

    public AdminNewTimeLine(TimelineMapper timelineMapper, UpdateWebInfoService updateWebInfoService) {
        this.timelineMapper = timelineMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    /**
     *
     * @return 读取数据库中时间线内容后输出，输出时与数据库存储相反，所以逆置
     */
    @GetMapping("/admin/newTimeline")
    public ModelAndView toNewTimeLine() {
        ModelAndView newTimeLine = new ModelAndView();

        boolean isUpdate = updateWebInfoService.addVisitsOne();
        if (!isUpdate) {
            throw new RuntimeException("数据库更新失败");
        }

        QueryWrapper<Timeline> timelineQueryWrapper = new QueryWrapper<>();
        timelineQueryWrapper.groupBy("index_id");
        List<Timeline> timelines = timelineMapper.selectList(timelineQueryWrapper);

        //逆置列表
        Collections.reverse(timelines);

        //之后还得加上时间线里的列表输出
        newTimeLine.addObject("timelines",timelines);

        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        newTimeLine.addObject("format", toStringFormat);

        newTimeLine.setViewName("admin/newTimeline");
        return newTimeLine;
    }

    /**
     *
     * @param title 时间线内容
     * @param response 跳转所需参数
     * @throws IOException 跳转会抛出的异常
     */
    @PostMapping("/admin/newTimeline")
    public void addNewTimeline(@Param("title") String title, HttpServletResponse response) throws IOException {

        Timeline timeline = new Timeline();
        timeline.setTime(new Date());
        timeline.setTimeText(title);
        int insert = timelineMapper.insert(timeline);
        if (insert != 1) {
            throw new RuntimeException("插入失败！");
        }

        response.sendRedirect("/admin/newTimeline");
    }

}
