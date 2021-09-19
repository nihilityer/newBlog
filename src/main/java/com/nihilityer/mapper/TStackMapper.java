package com.nihilityer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nihilityer.entity.TStack;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName com.nihilityer.mapper
 * @ClassName TStackMapper
 * @Description 技术栈实体mapper接口
 * @Author nihilityer
 * @Date 2021/9/13 9:56
 */
@Mapper
public interface TStackMapper extends BaseMapper<TStack> {
}
