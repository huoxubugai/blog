package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBlogId(Long id);
}
