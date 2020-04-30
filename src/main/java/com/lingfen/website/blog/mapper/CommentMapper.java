package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Comment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    List<Comment> getCommentsByBlogId(@Param("blogId") Long id);

    List<Comment> getReplyCommentsBycommentId(@Param("blogId") Long blogId, @Param("commentId") Integer commentId);
}
