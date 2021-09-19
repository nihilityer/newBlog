package com.nihilityer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nihilityer.entity.Timeline;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName com.nihilityer.mapper
 * @ClassName BlogMapper
 * @Description 时间线实体的mapper接口
 * @Author nihilityer
 * @Date 2021/9/13 9:57
 */
@Mapper
public interface TimelineMapper extends BaseMapper<Timeline> {
}
