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

    Blog getBlogById(Long id);

    int updateBlog(Blog blog);

    List<PreviewBlog> getPreviewBlog();

    List<RecommendPreviewBlog> getRecommendPreviewBlog(Integer recommendBlogNums);

    List<PreviewBlog> getPreviewBlogByType(Integer typeId);

    List<PreviewBlog> getPreviewBlogByTag(Integer tagId);

    int getTotalPublishedBlogNums();

    Map<String, List<ArchivesBlogBean>> getArchivesBlog();

    Blog getMdBlogById(Long id);

    int getTypeIdByBlogId(Long id);
}
