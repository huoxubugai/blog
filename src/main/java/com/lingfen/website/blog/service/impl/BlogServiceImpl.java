package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.helpbean.ArchivesBlogBean;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.bean.helpbean.RecommendPreviewBlog;
import com.lingfen.website.blog.exception.NotFoundException;
import com.lingfen.website.blog.mapper.BlogMapper;
import com.lingfen.website.blog.mapper.TypeMapper;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.BadLocationException;
import java.util.*;

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
        Blog blog = new Blog();
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
    public Blog getBlogById(Long id) {
        Blog blog = new Blog();
        blog.setId(id);
        return blogMapper.selectByPrimaryKey(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        Date date = new Date();
        blog.setUpdateTime(date);
        int result = blogMapper.updateBlog(blog);
        return result;
    }

    private List<Integer> convertToList(String ids) {
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<PreviewBlog> getPreviewBlog() {
        List<PreviewBlog> previewBlogs = blogMapper.getPreviewBlog();
        return previewBlogs;
    }

    @Override
    public List<RecommendPreviewBlog> getRecommendPreviewBlog(Integer recommendBlogNums) {
        List<RecommendPreviewBlog> recommendPreviewBlogs = blogMapper.getRecommendPreviewBlog(recommendBlogNums);
        return recommendPreviewBlogs;
    }

    @Override
    public List<PreviewBlog> getPreviewBlogByType(Integer typeId) {

        List<PreviewBlog> previewBlogs = blogMapper.getPreviewBlogByType(typeId);
        return previewBlogs;
    }

    @Override
    public List<PreviewBlog> getPreviewBlogByTag(Integer tagId) {
        List<PreviewBlog> previewBlogs = blogMapper.getPreviewBlogByTag(tagId);
        return previewBlogs;
    }

    @Override
    public int getTotalPublishedBlogNums() {
       int nums= blogMapper.getTotalPublishedBlogNums();
        return nums;
    }

    @Override
    public Map<String, List<ArchivesBlogBean>> getArchivesBlog() {
        Map<String, List<ArchivesBlogBean>> archivesBlog = new LinkedHashMap<>(); //使用linkedHashMap保证按输入的顺序输出，为了让年份从大到小展示博客
        List<String> years = blogMapper.getBlogYears(); //拿到博客的年份列表
        List<ArchivesBlogBean> archivesBlogBeansByYear; //每一年份对应的归档博客
        for (String year: years) {
            archivesBlogBeansByYear = blogMapper.getArchivesBlog(year); //根据year拿到该年份的归档博客
            archivesBlog.put(year, archivesBlogBeansByYear); //将年份和对应的归档博客按k v放进map中
        }
        return archivesBlog;
    }

    @Override
    public Blog getMdBlogById(Long id) {
        Blog blog = getBlogById(id);
        if (blog == null)
            throw new NotFoundException("该博客不存在");
        Blog mdBlog = new Blog();
        BeanUtils.copyProperties(blog, mdBlog);
        String content = mdBlog.getContent();
        mdBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //更新浏览量 +1
        int i = blogMapper.updateViews(id);
        return mdBlog;
    }

    @Override
    public int getTypeIdByBlogId(Long id) {
        int typeId = blogMapper.getTypeIdByBlogId(id);
        return typeId;
    }
}
