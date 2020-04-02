package com.lingfen.website.blog.search;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BlogSearchRepository extends ElasticsearchRepository<BlogSearchBean, Integer> {
    List<BlogSearchBean> findByTitleOrContentLike(String searchFromTitle, String searchFromContent);

    List<BlogSearchBean> findByTitleLike(String title);

    List<BlogSearchBean> queryByTitleOrContent(String s1, String s2);

    List<BlogSearchBean> findByTitleOrContent(String s1, String s2);


    List<BlogSearchBean> findBlogSearchBeanByTitleOrContent(String searchFromTitle, String searchFromContent);


}
