package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTag();

    Tag selectTagByName(String name);

    int saveTag(Tag tag);

    Tag selectTagById(Integer id);

    int deleteTag(Integer id);

    List<Tag> getPreViewTag(Integer nums);

    int getMaxNumsBlogTagId();

    int updateTag(Tag tag);


    List<Integer> getTagIdsByBlogId(Long blogId);

    List<Tag> getTagsByTagIds(List<Integer> tagIds);

    List<Integer> getTagIds();

    int updateBlogNumsByTagId(Integer tagId);
}
