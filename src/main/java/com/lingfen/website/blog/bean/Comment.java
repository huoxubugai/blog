package com.lingfen.website.blog.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String nickname;
    private String contact;//联系方式
    private long blogId; //对应的博客id
    private String blogTitle; //对应的博客标题
    private String avatar;
    private Date createTime;

    private Boolean blogHolder;//是否是博主


    private Integer parentCommentId;
//    private String parentNickname;


    //回复评论
    @Transient
    private List<Comment> replyComments = new ArrayList<>();//一条父评论对应一个回复评论的集合
    @Transient
    private Comment parentComment; //一条回复评论持有父评论对象


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

//    public String getParentNickname() {
//        return parentNickname;
//    }
//
//    public void setParentNickname(String parentNickname) {
//        this.parentNickname = parentNickname;
//    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Boolean getBlogHolder() {
        return blogHolder;
    }

    public void setBlogHolder(Boolean blogHolder) {
        this.blogHolder = blogHolder;
    }

}
