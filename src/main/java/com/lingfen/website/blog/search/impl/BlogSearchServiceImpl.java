package com.lingfen.website.blog.search.impl;

import com.lingfen.website.blog.search.BlogSearchBean;
import com.lingfen.website.blog.search.BlogSearchRepository;
import com.lingfen.website.blog.search.BlogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogSearchServiceImpl implements BlogSearchService {
    @Autowired
    BlogSearchRepository blogSearchRepository;

    @Override
    public List<BlogSearchBean> searchBlog(String searchContent) {
        List<BlogSearchBean> searchBeanList = blogSearchRepository.findByTitleOrContentLike(searchContent, searchContent);
        return searchBeanList;
    }
}
