package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.bean.helpbean.RecommendPreviewBlog;
import com.lingfen.website.blog.mapper.BlogMapper;
import com.lingfen.website.blog.mapper.TypeMapper;
import com.lingfen.website.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Blog> getAllBlog() {
        List<Blog> blogs = blogMapper.selectAll();
        //根据typeId拿到type，再把type放回到blog对象里
        for (Blog blog : blogs) {
            Type type = typeMapper.selectTypeById(blog.getTypeId());
            blog.setType(type);
        }
        return blogs;
    }

    @Override
    public int deleteBlogById(Long id) {
        Blog blog=new Blog();
        blog.setId(id);
        int result = blogMapper.deleteByPrimaryKey(blog);
        return result;
    }

    @Override
    public int savaBlog(Blog blog) {
        int result = blogMapper.insertSelective(blog);
        return result;
    }

    @Override
    public Blog getBlogById(long id) {
        Blog blog=new Blog();
        blog.setId(id);
        return blogMapper.selectByPrimaryKey(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        int result = blogMapper.updateBlog(blog);
        return result;
    }

    @Override
    public List<PreviewBlog> getPreviewBlog() {
        List<PreviewBlog> previewBlogs = blogMapper.getPreviewBlog();
        return previewBlogs;
    }

    @Override
    public List<RecommendPreviewBlog> getRecommendPreviewBlog(int recommendBlogNums) {
       List<RecommendPreviewBlog> recommendPreviewBlogs= blogMapper.getRecommendPreviewBlog(recommendBlogNums);
        return recommendPreviewBlogs;
    }
}
