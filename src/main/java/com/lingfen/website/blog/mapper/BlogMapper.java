package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.bean.helpbean.RecommendPreviewBlog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {
    int updateBlog(@Param("blog") Blog blog);

    List<PreviewBlog> getPreviewBlog();

    List<RecommendPreviewBlog> getRecommendPreviewBlog(@Param("recommendBlogNums") int recommendBlogNums);

    List<PreviewBlog> getPreviewBlogByType(@Param("typeId") int typeId);

    List<PreviewBlog> getPreviewBlogByTag(@Param("tagId") int tagId);
}
