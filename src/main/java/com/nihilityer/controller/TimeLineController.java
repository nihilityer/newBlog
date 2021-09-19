package com.nihilityer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Timeline;
import com.nihilityer.mapper.TimelineMapper;
import com.nihilityer.service.UpdateWebInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName TimeLineController
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 14:50
 */
@RestController
public class TimeLineController {

    private final UpdateWebInfoService updateWebInfoService;

    private final TimelineMapper timelineMapper;

    public TimeLineController(TimelineMapper timelineMapper, UpdateWebInfoService updateWebInfoService) {
        this.timelineMapper = timelineMapper;
        this.updateWebInfoService = updateWebInfoService;
    }

    @GetMapping("/timeline")
    public ModelAndView toTimeLine() {
        ModelAndView timeline = new ModelAndView();

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
        timeline.addObject("timelines",timelines);

        SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        timeline.addObject("format", toStringFormat);

        timeline.setViewName("timeline");
        return timeline;
    }
}
