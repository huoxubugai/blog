package com.lingfen.website.blog;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.search.BlogSearchBean;
import com.lingfen.website.blog.search.BlogSearchRepository;
import com.lingfen.website.blog.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    BlogSearchRepository blogSearchRepository;
    @Autowired
    BlogService blogService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void addDataToES() {
        List<Blog> blogs = blogService.getAllBlog();
        List<BlogSearchBean> blogSearchBeans = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogSearchBean blogSearchBean = new BlogSearchBean();
            blogSearchBean.setId(blog.getId());
            blogSearchBean.setTitle(blog.getTitle());
            blogSearchBean.setContent(blog.getContent());
            blogSearchBean.setAppreciation(blog.isAppreciation());
            blogSearchBean.setCommentabled(blog.isCommentabled());
            blogSearchBean.setCreateTime(blog.getCreateTime());
            blogSearchBean.setUpdateTime(blog.getUpdateTime());
            blogSearchBean.setDescription(blog.getDescription());
            blogSearchBean.setFirstPicture(blog.getFirstPicture());
            blogSearchBean.setFlag(blog.getFlag());
            blogSearchBean.setPublished(blog.isPublished());
            blogSearchBean.setRecommend(blog.isRecommend());
            blogSearchBean.setShareStatement(blog.isShareStatement());
            blogSearchBean.setTagIds(blog.getTagIds());
            blogSearchBean.setTypeId(blog.getTypeId());
            blogSearchBean.setUserId(blog.getUserId());
            blogSearchBean.setViews(blog.getViews());
            blogSearchBeans.add(blogSearchBean);
        }
        BlogSearchBean blog = new BlogSearchBean();
        for (BlogSearchBean blogSearchBean : blogSearchBeans) {
            blogSearchRepository.index(blogSearchBean);
        }
    }

    @Test
    public void search() {
        List<BlogSearchBean> blogSearchBeans = blogSearchRepository.findByTitleLike("es");
        for (BlogSearchBean blogSearchBean : blogSearchBeans) {
            System.out.println(blogSearchBean);
        }
    }

    @Test
    public void addBlog() {
        BlogSearchBean blogSearchBean = new BlogSearchBean();
        blogSearchBean.setId(101l);
        blogSearchBean.setTitle("es测试2");
        blogSearchBean.setContent("ss测试内容2");
        blogSearchBean.setViews(223);
        blogSearchRepository.index(blogSearchBean);
    }

    @Test
    public void delete() {
        blogSearchRepository.deleteAll();
    }

    @Test
    public void findByTitleAndContent() {
//        List<BlogSearchBean> result = blogSearchRepository.queryByTitleOrContent("ss","ss");
//        List<BlogSearchBean> result2 = blogSearchRepository.findByTitleOrContent("ss","ss");
        List<BlogSearchBean> result3 = blogSearchRepository.findByTitleOrContentLike("测试", "测试");

        for (BlogSearchBean blogSearchBean : result3) {
            System.out.println(blogSearchBean);
        }
    }
}
