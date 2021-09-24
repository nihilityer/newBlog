package com.nihilityer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId("user_id")
    private String userId;
    @TableId("user_name")
    private String userName;
    @TableId("user_password")
    private String userPassword;
    @TableId("user_sex")
    private Integer userSex;
    @TableId("user_role")
    private String userRole;
}
