package com.lingfen.website.blog.schedule;

import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.search.BlogSearchBean;
import com.lingfen.website.blog.search.BlogSearchRepository;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.service.TagService;
import com.lingfen.website.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务
 */
@Service
public class ScheduleService {
    @Autowired
    TypeService typeService;
    @Autowired
    BlogSearchRepository blogSearchRepository;
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;

    /**
     * 更新type表中的博客数量，因为博客数量由于博客数的同时增加和减少没有做成原子性的
     * 操作，因此可能出现偏差，每天凌晨1.00定时更新
     * cron格式为 秒 分 时 日 月 周几
     */
    @Scheduled(cron = "0 0 1 * * *") //每天凌晨一点更新
    public void updateBlogNumsInTableType() {
        int result = typeService.updateBlogNumsToLatest();
        if (result != 0)
            System.out.println("****" + new Date() + "更新博客数量成功" + "****");
        else
            System.out.println("****" + new Date() + "更新博客数量失败" + "****");
    }

    /**
     * 每隔一个小时将mysql数据更新到Es
     */
    @Scheduled(cron = "0 0 * * * *")
    public void addMysqlDataToEs() {
        deleteEsData(); //因为会有冗余数据存在，先全部清空，但是会造成在该时刻搜索不到数据的情况
        addDataToEs(); //批量复制数据
    }

    private void deleteEsData() {
        blogSearchRepository.deleteAll();
    }

    private void addDataToEs() {
        List<Blog> blogs = blogService.getAllBlog();
        List<BlogSearchBean> blogSearchBeans = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogSearchBean blogSearchBean = new BlogSearchBean();
            blogSearchBean.setId(blog.getId());
            blogSearchBean.setTitle(blog.getTitle());
            blogSearchBean.setContent(blog.getContent());
            blogSearchBean.setAppreciation(blog.getAppreciation());
            blogSearchBean.setCommentabled(blog.getCommentabled());
            blogSearchBean.setCreateTime(blog.getCreateTime());
            blogSearchBean.setUpdateTime(blog.getUpdateTime());
            blogSearchBean.setDescription(blog.getDescription());
            blogSearchBean.setFirstPicture(blog.getFirstPicture());
            blogSearchBean.setFlag(blog.getFlag());
            blogSearchBean.setPublished(blog.getPublished());
            blogSearchBean.setRecommend(blog.getRecommend());
            blogSearchBean.setShareStatement(blog.getShareStatement());
            blogSearchBean.setTagIds(blog.getTagIds());
            blogSearchBean.setTypeId(blog.getTypeId());
            blogSearchBean.setUserId(blog.getUserId());
            blogSearchBean.setViews(blog.getViews());
            blogSearchBeans.add(blogSearchBean);
        }
        if (blogSearchBeans.size() > 0)
            System.out.println("********从Mysql向Es更新数据*********");
        for (BlogSearchBean blogSearchBean : blogSearchBeans) {
            blogSearchRepository.index(blogSearchBean);
        }
    }

    /**
     * 更新Tag表中对应的博客数量
     * 因为首页用到了根据博客数量前9显示TAG
     */
    @Scheduled(cron = "0 0 2 * * *") //每天凌晨两点更新
    public void updateBlogNumsInTag() {
        List<Integer> tagIds = tagService.getTagIds();
        for (Integer tagId : tagIds) {
            int result = tagService.updateBlogNumsByTagId(tagId);
            System.out.println(result);
        }
    }


}
