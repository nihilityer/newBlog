package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName Timeline
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 22:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("timeline")
public class Timeline {
    private Integer indexId;
    private Date time;
    private String timeText;
}
