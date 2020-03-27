package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Blog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BlogMapper extends Mapper<Blog> {
    int updateBlog(@Param("blog") Blog blog);
}
