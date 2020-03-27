package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlog();

    int deleteBlogById(Long id);

    int savaBlog(Blog blog);

    Blog getBlogById(long id);

    int updateBlog(Blog blog);
}
