package com.nihilityer.controller;

import com.nihilityer.entity.Users;
import com.nihilityer.mapper.UsersMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName com.nihilityer.controller
 * @ClassName RegisterController
 * @Description 注册页controller
 * @Author nihilityer
 * @Date 2021/9/27 21:05
 */
@RestController
public class RegisterController {

    private final UsersMapper usersMapper;

    public RegisterController(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @GetMapping("/register")
    public ModelAndView toRegister() {
        ModelAndView register = new ModelAndView();
        register.setViewName("register");
        return register;
    }

    /**
     *
     * @param userId 注册用户的id
     * @param userName 注册用户的昵称，为空时使用id代替
     * @param password 用户输入的密码
     * @param rePassword 用户的确认密码，当与密码不一样时不插入
     * @param response 用于成功后跳转
     * @throws IOException 跳转会抛出的异常
     */
    @PostMapping("register")
    public void addNewUser(@Param("userId")String userId,
                           @Param("userName")String userName,
                           @Param("password")String password,
                           @Param("rePassword")String rePassword,
                           HttpServletResponse response) throws IOException {

        if (password.equals(rePassword)) {
            Users users = new Users();
            users.setUserId(userId);
            if (userName != null) {
                users.setUserName(userName);
            } else {
                users.setUserName(userId);
            }
            users.setUserPassword(password);
            users.setUserRole("user");

            int insert = usersMapper.insert(users);
            if (insert == 1) {
                response.sendRedirect("/login");
            } else {
                response.sendRedirect("/register");
            }
        } else {
            response.sendRedirect("/register");
        }

    }

}
