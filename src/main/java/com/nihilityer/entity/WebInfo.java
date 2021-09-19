package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName WebInfo
 * @Description
 * @Author nihilityer
 * @Date 2021/9/16 20:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("webinfo")
public class WebInfo {
    private String setName;
    private Integer setNumber;
}
