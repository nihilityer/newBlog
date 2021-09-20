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

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName AdminNewTimeLine
 * @Description
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

    @PostMapping("/admin/newTimeline")
    public ModelAndView addNewTimeline(@Param("title") String title) {
        ModelAndView newTimeLine = new ModelAndView();

        Timeline timeline = new Timeline();
        timeline.setTime(new Date());
        timeline.setTimeText(title);
        int insert = timelineMapper.insert(timeline);
        if (insert != 1) {
            throw new RuntimeException("插入失败！");
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

}
