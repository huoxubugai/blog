package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.helpbean.ArchivesBlogBean;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.bean.helpbean.RecommendPreviewBlog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BlogMapper extends Mapper<Blog> {
    Integer updateBlog(@Param("blog") Blog blog);

    List<PreviewBlog> getPreviewBlog();

    List<RecommendPreviewBlog> getRecommendPreviewBlog(@Param("recommendBlogNums") Integer recommendBlogNums);

    List<PreviewBlog> getPreviewBlogByType(@Param("typeId") Integer typeId);

    List<PreviewBlog> getPreviewBlogByTag(@Param("tagId") Integer tagId);

    Integer getTotalPublishedBlogNums();


    List<String> getBlogYears();

    List<ArchivesBlogBean> getArchivesBlog(@Param("year") String year);

    Integer updateViews(@Param("blogId") Long id);

    Integer getTypeIdByBlogId(@Param("blogId") Long id);
}
