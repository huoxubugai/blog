package com.lingfen.website.blog.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Tag {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String blogNums;//该tag对应的博客数量

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogNums() {
        return blogNums;
    }

    public void setBlogNums(String blogNums) {
        this.blogNums = blogNums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
