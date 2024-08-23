package com.syj.myshop.auth.services;

import com.syj.myshop.auth.entity.SysUser;
import com.syj.myshop.auth.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author SuYajiang
 * @email suyajiang@aoscript.com
 * @create 2024-08-21 13:55
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         SysUser user =userMapper.getByUsername(username);
         if(user==null) {
             throw new UsernameNotFoundException(username +"不存在");
         }
         return  User.withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("---")
                .build();
    }



}
