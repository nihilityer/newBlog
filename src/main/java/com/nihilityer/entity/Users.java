package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @PackageName com.nihilityer.entity
 * @ClassName User
 * @Description user的实体类
 * @Author nihilityer
 * @Date 2021/9/8 21:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userinfo")
public class Users {
    private String userId;
    private String userName;
    private String userPassword;
    private Integer userSex;
    private String userRole;
}
