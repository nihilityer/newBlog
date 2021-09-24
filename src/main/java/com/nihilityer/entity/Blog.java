package com.nihilityer.entity;

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
    @TableId("blog_id")
    private Integer blogId;
    @TableId("blog_title")
    private String blogTitle;
    @TableId("creater")
    private String creater;
    @TableId("creater_id")
    private Integer createrId;
    @TableId("text")
    private String Text;
    @TableId("creater_time")
    private Date createTime;
}
