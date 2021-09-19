package com.nihilityer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nihilityer.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName com.nihilityer.mapper
 * @ClassName BlogMapper
 * @Description 博客实体的mapper接口
 * @Author nihilityer
 * @Date 2021/9/13 9:57
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
