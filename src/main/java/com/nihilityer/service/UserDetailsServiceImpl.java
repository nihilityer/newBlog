package com.nihilityer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.Users;
import com.nihilityer.mapper.UsersMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName com.nihilityer.service
 * @ClassName UserDetailsServiceImpl
 * @Description 登陆验证类
 * @Author nihilityer
 * @Date 2021/9/8 22:11
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersMapper usersMapper;

    public UserDetailsServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        QueryWrapper<Users> wrapper = new QueryWrapper<>();

        wrapper.eq("user_id", userId);
        Users users = usersMapper.selectOne(wrapper);

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(users.getUserRole());

        return new User(users.getUserName(),
                new BCryptPasswordEncoder().encode(users.getUserPassword()), auths);
    }
}
