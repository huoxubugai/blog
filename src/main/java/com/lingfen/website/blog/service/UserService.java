package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.User;

public interface UserService {
    User checkUser(String username, String password);
}
