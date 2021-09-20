package com.nihilityer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @PackageName com.nihilityer.config
 * @ClassName MySecurityConfig
 * @Description spring Security配置类
 * @Author nihilityer
 * @Date 2021/9/8 20:50
 */
@EnableWebSecurity
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public MySecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //各页面访问权限设置
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //hasAnyAuthority是有多个权限时使用，使用role的区别是在判断的时候会加上ROLE_去判断
                .antMatchers("/admin/**").hasAuthority("root")
                .and()

                //设置跳转登陆页
                .formLogin().loginPage("/login")
                //表单提交到的地址
                .loginProcessingUrl("/toLogin")
                //登陆成功后跳转的页面
                .defaultSuccessUrl("/admin/")
                .usernameParameter("userInput")
                .passwordParameter("password")
                .and()

                //开启注销功能
                .logout()
                .logoutSuccessUrl("/")
                .and()
                //不关闭的话注销会失败
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
