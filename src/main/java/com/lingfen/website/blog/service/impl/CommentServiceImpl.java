package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.Comment;
import com.lingfen.website.blog.mapper.CommentMapper;
import com.lingfen.website.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByBlogId(Long id) {
        List<Comment> comments = commentMapper.getCommentsByBlogId(id);
        for (Comment comment : comments) {
            List<Comment> replyComments = commentMapper.getReplyCommentsBycommentId(comment.getBlogId(), comment.getId());
            comment.setReplyComments(replyComments); //将拿到的回复评论设置给评论
            for (Comment replyComment : replyComments) {
                replyComment.setParentComment(comment);//再设置父评论给查出来的回复评论
            }
        }
        return comments;
    }
}
