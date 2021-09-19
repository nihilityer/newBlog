package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName TStack
 * @Description
 * @Author nihilityer
 * @Date 2021/9/8 22:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tstack")
public class TStack {
    private String name;
    private Integer grade;
}
