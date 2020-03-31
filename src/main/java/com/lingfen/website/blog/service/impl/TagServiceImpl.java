package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.Tag;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.mapper.TagMapper;
import com.lingfen.website.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> getAllTag() {
        List<Tag> tags = tagMapper.getAllTag();
        return tags;
    }

    @Override
    public Tag selectTagByName(String name) {
        Tag tag = tagMapper.selectTagByName(name);
        return tag;
    }

    @Override
    public int saveTag(Tag tag) {
        int i = tagMapper.insertSelective(tag);
        return i;
    }

    @Override
    public Tag selectTagById(int id) {
        Tag result = tagMapper.selectTagById(id);
        return result;
    }

    @Override
    public int deleteTag(int id) {
        int result = tagMapper.deleteTagById(id);
        return result;
    }

    @Override
    public List<Tag> getPreViewTag(int nums) {
        List<Tag> previewTags = tagMapper.getPreViewTag(nums);
        return previewTags;
    }

    @Override
    public int getMaxNumsBlogTagId() {
        int result = tagMapper.getMaxNumsBlogTagId();
        return result;
    }

    @Override
    public int updateTag(Tag tag) {
        int result = tagMapper.updateTag(tag);
        return result;
    }

    @Override
    public List<Integer> getTagIdsByBlogId(long blogId) {
        List<Integer> tagIds = tagMapper.getTagIdsByBlogId(blogId);
        return tagIds;
    }

    //根据tagId列表查出tag列表
    @Override
    public List<Tag> getTagsByTagIds(List<Integer> tagIds) {
        List<Tag> blogTags = new ArrayList<>();
        for (Integer tagId : tagIds) {
            Tag tag = tagMapper.getTagByTagId(tagId);
            blogTags.add(tag);
        }
        return blogTags;
    }


}
