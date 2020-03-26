package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User getUserByUsername(@Param("username") String username);
}
