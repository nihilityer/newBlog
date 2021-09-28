package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName Blog
 * @Description blog表的实体类
 * @Author nihilityer
 * @Date 2021/9/8 22:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog")
public class Blog {
    @TableId(value = "blog_id", type = IdType.AUTO)
    private String blogId;
    @TableId("blog_title")
    private String blogTitle;
    @TableId("creater")
    private String creater;
    @TableId("creater_id")
    private String createrId;
    @TableId("text")
    private String Text;
    @TableId("creater_time")
    private Date createTime;
}
