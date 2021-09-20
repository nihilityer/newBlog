package com.nihilityer.entity;

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
    private Integer blogId;
    private String blogTitle;
    private String creater;
    private Integer createrId;
    private String Text;
    private Date createTime;
}
