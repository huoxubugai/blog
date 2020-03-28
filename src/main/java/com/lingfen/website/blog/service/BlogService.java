package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.bean.helpbean.RecommendPreviewBlog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlog();

    int deleteBlogById(Long id);

    int savaBlog(Blog blog);

    Blog getBlogById(long id);

    int updateBlog(Blog blog);

    List<PreviewBlog> getPreviewBlog();

    List<RecommendPreviewBlog> getRecommendPreviewBlog(int recommendBlogNums);

}
