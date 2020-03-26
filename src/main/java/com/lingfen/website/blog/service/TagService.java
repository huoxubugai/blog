package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTag();

    Tag selectTagByName(String name);

    int saveTag(Tag tag);

    Tag selectTagById(int id);

    int deleteTag(int id);
}
