package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName Timeline
 * @Description timeline表的实体类
 * @Author nihilityer
 * @Date 2021/9/8 22:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("timeline")
public class Timeline {
    @TableId(value = "index_id", type = IdType.AUTO)
    private Integer indexId;
    @TableId("time")
    private Date time;
    @TableId("time_text")
    private String timeText;
}
