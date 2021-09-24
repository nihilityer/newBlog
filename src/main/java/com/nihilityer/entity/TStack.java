package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName TStack
 * @Description tstack表的实体类
 * @Author nihilityer
 * @Date 2021/9/8 22:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tstack")
public class TStack {
    @TableId("name")
    private String name;
    @TableId("grade")
    private Integer grade;
}
