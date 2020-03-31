package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTag();

    Tag selectTagByName(String name);

    int saveTag(Tag tag);

    Tag selectTagById(int id);

    int deleteTag(int id);

    List<Tag> getPreViewTag(int nums);

    int getMaxNumsBlogTagId();

    int updateTag(Tag tag);


    List<Integer> getTagIdsByBlogId(long blogId);

    List<Tag> getTagsByTagIds(List<Integer> tagIds);
}
