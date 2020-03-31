package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.helpbean.ArchivesBlogBean;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.bean.helpbean.RecommendPreviewBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> getAllBlog();

    int deleteBlogById(Long id);

    int savaBlog(Blog blog);

    Blog getBlogById(long id);

    int updateBlog(Blog blog);

    List<PreviewBlog> getPreviewBlog();

    List<RecommendPreviewBlog> getRecommendPreviewBlog(int recommendBlogNums);

    List<PreviewBlog> getPreviewBlogByType(int typeId);

    List<PreviewBlog> getPreviewBlogByTag(int tagId);

    int getTotalPublishedBlogNums();

    Map<String, List<ArchivesBlogBean>> getArchivesBlog();

    Blog getMdBlogById(Long id);
}
