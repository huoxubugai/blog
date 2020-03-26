package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.User;
import com.lingfen.website.blog.mapper.UserMapper;
import com.lingfen.website.blog.service.UserService;
import com.lingfen.website.blog.utils.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        return user==null?null:user.getPassword().equals(MD5util.code(password))?user:null;
    }
}
